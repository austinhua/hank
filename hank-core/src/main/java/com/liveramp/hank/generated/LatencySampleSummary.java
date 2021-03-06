/**
 * Autogenerated by Thrift Compiler (1.0.0-dev)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.liveramp.hank.generated;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.EnumMap;
import java.util.Set;
import java.util.HashSet;
import java.util.EnumSet;
import java.util.Collections;
import java.util.BitSet;
import java.nio.ByteBuffer;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LatencySampleSummary implements org.apache.thrift.TBase<LatencySampleSummary, LatencySampleSummary._Fields>, java.io.Serializable, Cloneable, Comparable<LatencySampleSummary> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("LatencySampleSummary");

  private static final org.apache.thrift.protocol.TField MINIMUM_FIELD_DESC = new org.apache.thrift.protocol.TField("minimum", org.apache.thrift.protocol.TType.DOUBLE, (short)1);
  private static final org.apache.thrift.protocol.TField MAXIMUM_FIELD_DESC = new org.apache.thrift.protocol.TField("maximum", org.apache.thrift.protocol.TType.DOUBLE, (short)2);
  private static final org.apache.thrift.protocol.TField NUM_VALUES_FIELD_DESC = new org.apache.thrift.protocol.TField("num_values", org.apache.thrift.protocol.TType.I64, (short)3);
  private static final org.apache.thrift.protocol.TField TOTAL_FIELD_DESC = new org.apache.thrift.protocol.TField("total", org.apache.thrift.protocol.TType.DOUBLE, (short)4);
  private static final org.apache.thrift.protocol.TField DECILES_FIELD_DESC = new org.apache.thrift.protocol.TField("deciles", org.apache.thrift.protocol.TType.LIST, (short)6);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new LatencySampleSummaryStandardSchemeFactory());
    schemes.put(TupleScheme.class, new LatencySampleSummaryTupleSchemeFactory());
  }

  public double minimum; // required
  public double maximum; // required
  public long num_values; // required
  public double total; // required
  public List<Double> deciles; // optional

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    MINIMUM((short)1, "minimum"),
    MAXIMUM((short)2, "maximum"),
    NUM_VALUES((short)3, "num_values"),
    TOTAL((short)4, "total"),
    DECILES((short)6, "deciles");

    private static final Map<String, _Fields> byName = new HashMap<String, _Fields>();

    static {
      for (_Fields field : EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // MINIMUM
          return MINIMUM;
        case 2: // MAXIMUM
          return MAXIMUM;
        case 3: // NUM_VALUES
          return NUM_VALUES;
        case 4: // TOTAL
          return TOTAL;
        case 6: // DECILES
          return DECILES;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final String _fieldName;

    _Fields(short thriftId, String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __MINIMUM_ISSET_ID = 0;
  private static final int __MAXIMUM_ISSET_ID = 1;
  private static final int __NUM_VALUES_ISSET_ID = 2;
  private static final int __TOTAL_ISSET_ID = 3;
  private byte __isset_bitfield = 0;
  private _Fields optionals[] = {_Fields.DECILES};
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.MINIMUM, new org.apache.thrift.meta_data.FieldMetaData("minimum", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.MAXIMUM, new org.apache.thrift.meta_data.FieldMetaData("maximum", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.NUM_VALUES, new org.apache.thrift.meta_data.FieldMetaData("num_values", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.TOTAL, new org.apache.thrift.meta_data.FieldMetaData("total", org.apache.thrift.TFieldRequirementType.REQUIRED, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE)));
    tmpMap.put(_Fields.DECILES, new org.apache.thrift.meta_data.FieldMetaData("deciles", org.apache.thrift.TFieldRequirementType.OPTIONAL, 
        new org.apache.thrift.meta_data.ListMetaData(org.apache.thrift.protocol.TType.LIST, 
            new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.DOUBLE))));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(LatencySampleSummary.class, metaDataMap);
  }

  public LatencySampleSummary() {
  }

  public LatencySampleSummary(
    double minimum,
    double maximum,
    long num_values,
    double total)
  {
    this();
    this.minimum = minimum;
    set_minimum_isSet(true);
    this.maximum = maximum;
    set_maximum_isSet(true);
    this.num_values = num_values;
    set_num_values_isSet(true);
    this.total = total;
    set_total_isSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public LatencySampleSummary(LatencySampleSummary other) {
    __isset_bitfield = other.__isset_bitfield;
    this.minimum = other.minimum;
    this.maximum = other.maximum;
    this.num_values = other.num_values;
    this.total = other.total;
    if (other.is_set_deciles()) {
      List<Double> __this__deciles = new ArrayList<Double>();
      for (Double other_element : other.deciles) {
        __this__deciles.add(other_element);
      }
      this.deciles = __this__deciles;
    }
  }

  public LatencySampleSummary deepCopy() {
    return new LatencySampleSummary(this);
  }

  @Override
  public void clear() {
    set_minimum_isSet(false);
    this.minimum = 0.0;
    set_maximum_isSet(false);
    this.maximum = 0.0;
    set_num_values_isSet(false);
    this.num_values = 0;
    set_total_isSet(false);
    this.total = 0.0;
    this.deciles = null;
  }

  public double get_minimum() {
    return this.minimum;
  }

  public LatencySampleSummary set_minimum(double minimum) {
    this.minimum = minimum;
    set_minimum_isSet(true);
    return this;
  }

  public void unset_minimum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MINIMUM_ISSET_ID);
  }

  /** Returns true if field minimum is set (has been assigned a value) and false otherwise */
  public boolean is_set_minimum() {
    return EncodingUtils.testBit(__isset_bitfield, __MINIMUM_ISSET_ID);
  }

  public void set_minimum_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MINIMUM_ISSET_ID, value);
  }

  public double get_maximum() {
    return this.maximum;
  }

  public LatencySampleSummary set_maximum(double maximum) {
    this.maximum = maximum;
    set_maximum_isSet(true);
    return this;
  }

  public void unset_maximum() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __MAXIMUM_ISSET_ID);
  }

  /** Returns true if field maximum is set (has been assigned a value) and false otherwise */
  public boolean is_set_maximum() {
    return EncodingUtils.testBit(__isset_bitfield, __MAXIMUM_ISSET_ID);
  }

  public void set_maximum_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __MAXIMUM_ISSET_ID, value);
  }

  public long get_num_values() {
    return this.num_values;
  }

  public LatencySampleSummary set_num_values(long num_values) {
    this.num_values = num_values;
    set_num_values_isSet(true);
    return this;
  }

  public void unset_num_values() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __NUM_VALUES_ISSET_ID);
  }

  /** Returns true if field num_values is set (has been assigned a value) and false otherwise */
  public boolean is_set_num_values() {
    return EncodingUtils.testBit(__isset_bitfield, __NUM_VALUES_ISSET_ID);
  }

  public void set_num_values_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __NUM_VALUES_ISSET_ID, value);
  }

  public double get_total() {
    return this.total;
  }

  public LatencySampleSummary set_total(double total) {
    this.total = total;
    set_total_isSet(true);
    return this;
  }

  public void unset_total() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __TOTAL_ISSET_ID);
  }

  /** Returns true if field total is set (has been assigned a value) and false otherwise */
  public boolean is_set_total() {
    return EncodingUtils.testBit(__isset_bitfield, __TOTAL_ISSET_ID);
  }

  public void set_total_isSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __TOTAL_ISSET_ID, value);
  }

  public int get_deciles_size() {
    return (this.deciles == null) ? 0 : this.deciles.size();
  }

  public java.util.Iterator<Double> get_deciles_iterator() {
    return (this.deciles == null) ? null : this.deciles.iterator();
  }

  public void add_to_deciles(double elem) {
    if (this.deciles == null) {
      this.deciles = new ArrayList<Double>();
    }
    this.deciles.add(elem);
  }

  public List<Double> get_deciles() {
    return this.deciles;
  }

  public LatencySampleSummary set_deciles(List<Double> deciles) {
    this.deciles = deciles;
    return this;
  }

  public void unset_deciles() {
    this.deciles = null;
  }

  /** Returns true if field deciles is set (has been assigned a value) and false otherwise */
  public boolean is_set_deciles() {
    return this.deciles != null;
  }

  public void set_deciles_isSet(boolean value) {
    if (!value) {
      this.deciles = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case MINIMUM:
      if (value == null) {
        unset_minimum();
      } else {
        set_minimum((Double)value);
      }
      break;

    case MAXIMUM:
      if (value == null) {
        unset_maximum();
      } else {
        set_maximum((Double)value);
      }
      break;

    case NUM_VALUES:
      if (value == null) {
        unset_num_values();
      } else {
        set_num_values((Long)value);
      }
      break;

    case TOTAL:
      if (value == null) {
        unset_total();
      } else {
        set_total((Double)value);
      }
      break;

    case DECILES:
      if (value == null) {
        unset_deciles();
      } else {
        set_deciles((List<Double>)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case MINIMUM:
      return Double.valueOf(get_minimum());

    case MAXIMUM:
      return Double.valueOf(get_maximum());

    case NUM_VALUES:
      return Long.valueOf(get_num_values());

    case TOTAL:
      return Double.valueOf(get_total());

    case DECILES:
      return get_deciles();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case MINIMUM:
      return is_set_minimum();
    case MAXIMUM:
      return is_set_maximum();
    case NUM_VALUES:
      return is_set_num_values();
    case TOTAL:
      return is_set_total();
    case DECILES:
      return is_set_deciles();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof LatencySampleSummary)
      return this.equals((LatencySampleSummary)that);
    return false;
  }

  public boolean equals(LatencySampleSummary that) {
    if (that == null)
      return false;

    boolean this_present_minimum = true;
    boolean that_present_minimum = true;
    if (this_present_minimum || that_present_minimum) {
      if (!(this_present_minimum && that_present_minimum))
        return false;
      if (this.minimum != that.minimum)
        return false;
    }

    boolean this_present_maximum = true;
    boolean that_present_maximum = true;
    if (this_present_maximum || that_present_maximum) {
      if (!(this_present_maximum && that_present_maximum))
        return false;
      if (this.maximum != that.maximum)
        return false;
    }

    boolean this_present_num_values = true;
    boolean that_present_num_values = true;
    if (this_present_num_values || that_present_num_values) {
      if (!(this_present_num_values && that_present_num_values))
        return false;
      if (this.num_values != that.num_values)
        return false;
    }

    boolean this_present_total = true;
    boolean that_present_total = true;
    if (this_present_total || that_present_total) {
      if (!(this_present_total && that_present_total))
        return false;
      if (this.total != that.total)
        return false;
    }

    boolean this_present_deciles = true && this.is_set_deciles();
    boolean that_present_deciles = true && that.is_set_deciles();
    if (this_present_deciles || that_present_deciles) {
      if (!(this_present_deciles && that_present_deciles))
        return false;
      if (!this.deciles.equals(that.deciles))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    HashCodeBuilder builder = new HashCodeBuilder();

    boolean present_minimum = true;
    builder.append(present_minimum);
    if (present_minimum)
      builder.append(minimum);

    boolean present_maximum = true;
    builder.append(present_maximum);
    if (present_maximum)
      builder.append(maximum);

    boolean present_num_values = true;
    builder.append(present_num_values);
    if (present_num_values)
      builder.append(num_values);

    boolean present_total = true;
    builder.append(present_total);
    if (present_total)
      builder.append(total);

    boolean present_deciles = true && (is_set_deciles());
    builder.append(present_deciles);
    if (present_deciles)
      builder.append(deciles);

    return builder.toHashCode();
  }

  @Override
  public int compareTo(LatencySampleSummary other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(is_set_minimum()).compareTo(other.is_set_minimum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_minimum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.minimum, other.minimum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_maximum()).compareTo(other.is_set_maximum());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_maximum()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.maximum, other.maximum);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_num_values()).compareTo(other.is_set_num_values());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_num_values()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.num_values, other.num_values);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_total()).compareTo(other.is_set_total());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_total()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.total, other.total);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(is_set_deciles()).compareTo(other.is_set_deciles());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (is_set_deciles()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.deciles, other.deciles);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    schemes.get(iprot.getScheme()).getScheme().read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    schemes.get(oprot.getScheme()).getScheme().write(oprot, this);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder("LatencySampleSummary(");
    boolean first = true;

    sb.append("minimum:");
    sb.append(this.minimum);
    first = false;
    if (!first) sb.append(", ");
    sb.append("maximum:");
    sb.append(this.maximum);
    first = false;
    if (!first) sb.append(", ");
    sb.append("num_values:");
    sb.append(this.num_values);
    first = false;
    if (!first) sb.append(", ");
    sb.append("total:");
    sb.append(this.total);
    first = false;
    if (is_set_deciles()) {
      if (!first) sb.append(", ");
      sb.append("deciles:");
      if (this.deciles == null) {
        sb.append("null");
      } else {
        sb.append(this.deciles);
      }
      first = false;
    }
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // alas, we cannot check 'minimum' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'maximum' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'num_values' because it's a primitive and you chose the non-beans generator.
    // alas, we cannot check 'total' because it's a primitive and you chose the non-beans generator.
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class LatencySampleSummaryStandardSchemeFactory implements SchemeFactory {
    public LatencySampleSummaryStandardScheme getScheme() {
      return new LatencySampleSummaryStandardScheme();
    }
  }

  private static class LatencySampleSummaryStandardScheme extends StandardScheme<LatencySampleSummary> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, LatencySampleSummary struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // MINIMUM
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.minimum = iprot.readDouble();
              struct.set_minimum_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // MAXIMUM
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.maximum = iprot.readDouble();
              struct.set_maximum_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // NUM_VALUES
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.num_values = iprot.readI64();
              struct.set_num_values_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // TOTAL
            if (schemeField.type == org.apache.thrift.protocol.TType.DOUBLE) {
              struct.total = iprot.readDouble();
              struct.set_total_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 6: // DECILES
            if (schemeField.type == org.apache.thrift.protocol.TType.LIST) {
              {
                org.apache.thrift.protocol.TList _list68 = iprot.readListBegin();
                struct.deciles = new ArrayList<Double>(_list68.size);
                for (int _i69 = 0; _i69 < _list68.size; ++_i69)
                {
                  double _elem70; // required
                  _elem70 = iprot.readDouble();
                  struct.deciles.add(_elem70);
                }
                iprot.readListEnd();
              }
              struct.set_deciles_isSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      if (!struct.is_set_minimum()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'minimum' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.is_set_maximum()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'maximum' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.is_set_num_values()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'num_values' was not found in serialized data! Struct: " + toString());
      }
      if (!struct.is_set_total()) {
        throw new org.apache.thrift.protocol.TProtocolException("Required field 'total' was not found in serialized data! Struct: " + toString());
      }
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, LatencySampleSummary struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(MINIMUM_FIELD_DESC);
      oprot.writeDouble(struct.minimum);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(MAXIMUM_FIELD_DESC);
      oprot.writeDouble(struct.maximum);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(NUM_VALUES_FIELD_DESC);
      oprot.writeI64(struct.num_values);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(TOTAL_FIELD_DESC);
      oprot.writeDouble(struct.total);
      oprot.writeFieldEnd();
      if (struct.deciles != null) {
        if (struct.is_set_deciles()) {
          oprot.writeFieldBegin(DECILES_FIELD_DESC);
          {
            oprot.writeListBegin(new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.DOUBLE, struct.deciles.size()));
            for (double _iter71 : struct.deciles)
            {
              oprot.writeDouble(_iter71);
            }
            oprot.writeListEnd();
          }
          oprot.writeFieldEnd();
        }
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class LatencySampleSummaryTupleSchemeFactory implements SchemeFactory {
    public LatencySampleSummaryTupleScheme getScheme() {
      return new LatencySampleSummaryTupleScheme();
    }
  }

  private static class LatencySampleSummaryTupleScheme extends TupleScheme<LatencySampleSummary> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, LatencySampleSummary struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      oprot.writeDouble(struct.minimum);
      oprot.writeDouble(struct.maximum);
      oprot.writeI64(struct.num_values);
      oprot.writeDouble(struct.total);
      BitSet optionals = new BitSet();
      if (struct.is_set_deciles()) {
        optionals.set(0);
      }
      oprot.writeBitSet(optionals, 1);
      if (struct.is_set_deciles()) {
        {
          oprot.writeI32(struct.deciles.size());
          for (double _iter72 : struct.deciles)
          {
            oprot.writeDouble(_iter72);
          }
        }
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, LatencySampleSummary struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      struct.minimum = iprot.readDouble();
      struct.set_minimum_isSet(true);
      struct.maximum = iprot.readDouble();
      struct.set_maximum_isSet(true);
      struct.num_values = iprot.readI64();
      struct.set_num_values_isSet(true);
      struct.total = iprot.readDouble();
      struct.set_total_isSet(true);
      BitSet incoming = iprot.readBitSet(1);
      if (incoming.get(0)) {
        {
          org.apache.thrift.protocol.TList _list73 = new org.apache.thrift.protocol.TList(org.apache.thrift.protocol.TType.DOUBLE, iprot.readI32());
          struct.deciles = new ArrayList<Double>(_list73.size);
          for (int _i74 = 0; _i74 < _list73.size; ++_i74)
          {
            double _elem75; // required
            _elem75 = iprot.readDouble();
            struct.deciles.add(_elem75);
          }
        }
        struct.set_deciles_isSet(true);
      }
    }
  }

}

