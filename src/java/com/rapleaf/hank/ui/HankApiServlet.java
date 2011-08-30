package com.rapleaf.hank.ui;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.rapleaf.hank.coordinator.Coordinator;
import com.rapleaf.hank.coordinator.Domain;
import com.rapleaf.hank.coordinator.DomainGroup;
import com.rapleaf.hank.coordinator.DomainGroupVersion;
import com.rapleaf.hank.coordinator.DomainGroupVersionDomainVersion;
import com.rapleaf.hank.coordinator.DomainVersion;
import com.rapleaf.hank.coordinator.Ring;
import com.rapleaf.hank.coordinator.RingGroup;
import com.rapleaf.hank.coordinator.RingState;

public class HankApiServlet extends HttpServlet {

  public static class PARAMS {
    public static final String DOMAIN = "domain";
    public static final String DOMAIN_VERSION = "domain_version";
    public static final String DOMAIN_GROUP = "domain_group";
    public static final String DOMAIN_GROUP_VERSION = "domain_group_version";
    public static final String RING_GROUP = "ring_group";

    public static String[] getParamKeys() {
      return new String[] {DOMAIN, DOMAIN_VERSION, DOMAIN_GROUP, DOMAIN_GROUP_VERSION, RING_GROUP};
    }

    public static boolean paramsAreValid(Collection<String> params) {
      return true;
    }
  }

  static final String JSON_FORMAT = "application/json;charset=utf-8";

  private final Coordinator coordinator;

  public HankApiServlet(Coordinator coordinator) {
    this.coordinator = coordinator;
  }

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    doGet(request, response);
  }

  /**
   * Respond to GET requests.
   */
  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String responseBody;

    try {
      // Parse request data
      Map<String, Object> requestData = parseRequestParams(request, PARAMS.getParamKeys());
      Map<String, Object> responseData = getResponseData(requestData);
      responseBody = getJsonResponseBody(responseData).toString();
    } catch (Exception e) {
      response.sendError(400, "Error: " + e);
      return;
    }

    response.setContentType(JSON_FORMAT);
    response.setStatus(HttpServletResponse.SC_OK);
    response.getWriter().print(responseBody);
  }

  protected Map<String, Object> parseRequestParams(HttpServletRequest request, String[] paramKeys) {
    Map<String, Object> params = new HashMap<String, Object>();

    Map requestParamsMap = request.getParameterMap();
    for (String key : paramKeys) {
      if (requestParamsMap.containsKey(key)) {
        params.put(key, request.getParameter(key));
      }
    }

    return params;
  }

  public Map<String, Object> getResponseData(Map<String, Object> requestData) throws IOException {
    Map<String, Object> responseData =  new HashMap<String, Object>();
    if (requestData.containsKey(PARAMS.DOMAIN)) {
      if (requestData.containsKey(PARAMS.DOMAIN_VERSION)) {
        addDomainVersionDataToResponse(requestData, responseData);
      } else {
        addDomainDataToResponse(requestData, responseData);
      }
    }
    if (requestData.containsKey(PARAMS.RING_GROUP)) {
      addRingGroupDataToResponse(requestData, responseData);
    }
    if (requestData.containsKey(PARAMS.DOMAIN_GROUP)) {
      if (requestData.containsKey(PARAMS.DOMAIN_GROUP_VERSION)) {
        addDomainGroupVersionDataToResponse(requestData, responseData);
      } else {
        addDomainGroupDataToResponse(requestData, responseData);
      }
    }

    return responseData;
  }

  private void addRingGroupDataToResponse(Map<String, Object> requestData, Map<String, Object> responseData) throws IOException {
    RingGroup ringGroup = coordinator.getRingGroup((String) requestData.get(PARAMS.RING_GROUP));
    if (ringGroup != null){
      responseData.put(ringGroup.getName(), getRingGroupData(ringGroup));
    }
  }

  private Map<String, Object> getRingGroupData(RingGroup ringGroup) throws IOException {
    Map<String, Object> ringGroupData =  new HashMap<String, Object>();
    ringGroupData.put("name", ringGroup.getName());
    ringGroupData.put("current_version", ringGroup.getCurrentVersion());
    ringGroupData.put("updating_to_version", ringGroup.getUpdatingToVersion());
    ringGroupData.put("is_updating", ringGroup.isUpdating());
    ringGroupData.put("is_data_deployer_online", ringGroup.isDataDeployerOnline());
    ringGroupData.put("rings", getRingsMap(ringGroup.getRings()));
    return ringGroupData;
  }

  private Map<String, Object> getRingsMap(Collection<Ring> rings) throws IOException {
    Map<String, Object> ringsMap = new HashMap<String, Object>();
    for (Ring ring : rings) {
      ringsMap.put(String.valueOf(ring.getRingNumber()), getRingData(ring));
    }
    return ringsMap;
  }

  private Map<String, Object> getRingData(Ring ring) throws IOException {
    Map<String, Object> ringData =  new HashMap<String, Object>();
    ringData.put("ring_number", ring.getRingNumber());
    ringData.put("version_number", ring.getVersionNumber());
    ringData.put("updated_to_version", ring.getUpdatingToVersionNumber());
    ringData.put("is_update_pending", ring.isUpdatePending());
    ringData.put("status", ring.getState().name());
    return ringData;
  }

  private void addDomainDataToResponse(Map<String, Object> requestData, Map<String, Object> responseData) throws IOException {
    Domain domain = coordinator.getDomain((String) requestData.get(PARAMS.DOMAIN));
    if (domain != null){
      responseData.put(domain.getName(), getDomainData(domain));
    }
  }

  private void addDomainVersionDataToResponse(Map<String, Object> requestData, Map<String, Object> responseData) throws IOException {
    Domain domain = coordinator.getDomain((String) requestData.get(PARAMS.DOMAIN));
    try {
      DomainVersion version = domain.getVersionByNumber(Integer.valueOf((String) requestData.get(PARAMS.DOMAIN_VERSION)));
      responseData.put(String.valueOf(version.getVersionNumber()), getDomainVersionData(version));
    } catch (Exception ignored){} // No data added, but no harm done
  }

  private Map<String, Object> getDomainData(Domain domain) throws IOException {
    Map<String, Object> domainData =  new HashMap<String, Object>();
    domainData.put("name", domain.getName());
    domainData.put("num_partitions", domain.getNumParts());
    domainData.put("versions", getDomainVersionsMap(domain.getVersions()));
    return domainData;
  }

  private Map<String, Object> getDomainVersionsMap(Collection<DomainVersion> domainVersions) throws IOException {
    Map<String, Object> versionsMap =  new HashMap<String, Object>();
    for (DomainVersion v : domainVersions){
      versionsMap.put(String.valueOf(v.getVersionNumber()), getDomainVersionData(v));
    }
    return versionsMap;
  }

  private Map<String, Object> getDomainVersionData(DomainVersion version) throws IOException {
    Map<String, Object> versionData =  new HashMap<String, Object>();
    versionData.put("version_number", version.getVersionNumber());
    versionData.put("total_num_bytes", version.getTotalNumBytes());
    versionData.put("total_num_records", version.getTotalNumRecords());
    versionData.put("is_closed", version.isClosed());
    versionData.put("closed_at", version.getClosedAt());

    return versionData;
  }

  private Map<String, Object> getDomainGroupVersionData(DomainGroupVersion version) throws IOException {
    Map<String, Object> versionData =  new HashMap<String, Object>();
    versionData.put("version_number", version.getVersionNumber());
    versionData.put("domain_versions", getDomainGroupVersionDomainVersionsMap(version.getDomainVersions()));

    return versionData;
  }

  private Map<String, Object> getDomainGroupVersionDomainVersionsMap(Collection<DomainGroupVersionDomainVersion> versions) throws IOException {
    Map<String, Object> versionsMap =  new HashMap<String, Object>();
    for (DomainGroupVersionDomainVersion v : versions){
      versionsMap.put(v.getDomain().getName(), getDomainVersionData(v.getDomain().getVersionByNumber(v.getVersionNumber())));
    }
    return versionsMap;
  }

  private void addDomainGroupDataToResponse(Map<String, Object> requestData, Map<String, Object> responseData) throws IOException {
    DomainGroup domainGroup = coordinator.getDomainGroup((String) requestData.get(PARAMS.DOMAIN_GROUP));
    if (domainGroup != null){
      Map<String, Object> groupData =  new HashMap<String, Object>();
      groupData.put("name", domainGroup.getName());

      Map<String, Object> domainsMap =  new HashMap<String, Object>();
      for (Domain d : domainGroup.getDomains()){
        domainsMap.put(String.valueOf(d.getName()), getDomainData(d));
      }
      groupData.put("domains", domainsMap);

      Map<String, Object> versionsMap =  new HashMap<String, Object>();
      for (DomainGroupVersion v : domainGroup.getVersions()){
        versionsMap.put(String.valueOf(v.getVersionNumber()), getDomainGroupVersionData(v));
      }
      groupData.put("versions", versionsMap);

      responseData.put(domainGroup.getName(), groupData);
    }
  }

  private void addDomainGroupVersionDataToResponse(Map<String, Object> requestData, Map<String, Object> responseData) throws IOException {
    DomainGroup domainGroup = coordinator.getDomainGroup((String) requestData.get(PARAMS.DOMAIN_GROUP));
    try {
      DomainGroupVersion version = domainGroup.getVersionByNumber(Integer.valueOf((String) requestData.get(PARAMS.DOMAIN_GROUP_VERSION)));
      responseData.put(String.valueOf(version.getVersionNumber()), getDomainGroupVersionData(version));
    } catch (Exception ignored){} // No data added, but no harm done
  }

  private JSONObject getJsonResponseBody(Map<String, Object> data) throws JSONException, IOException {
    JSONObject json = new JSONObject();

    for (Map.Entry<String, Object> e : data.entrySet()){
      if (e.getValue() instanceof Map){
        json.put(e.getKey(), getJsonResponseBody((Map<String, Object>) e.getValue()));
      } else {
        json.put(e.getKey(), e.getValue());
      }
    }

    return json;
  }
}