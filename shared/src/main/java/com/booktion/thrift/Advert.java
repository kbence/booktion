/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.booktion.thrift;

import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.SchemeFactory;
import org.apache.thrift.scheme.StandardScheme;

import org.apache.thrift.scheme.TupleScheme;
import org.apache.thrift.protocol.TTupleProtocol;
import org.apache.thrift.protocol.TProtocolException;
import org.apache.thrift.EncodingUtils;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.server.AbstractNonblockingServer.*;
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

public class Advert implements org.apache.thrift.TBase<Advert, Advert._Fields>, java.io.Serializable, Cloneable, Comparable<Advert> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("Advert");

  private static final org.apache.thrift.protocol.TField ID_FIELD_DESC = new org.apache.thrift.protocol.TField("id", org.apache.thrift.protocol.TType.I32, (short)1);
  private static final org.apache.thrift.protocol.TField ISSUER_FIELD_DESC = new org.apache.thrift.protocol.TField("issuer", org.apache.thrift.protocol.TType.I32, (short)2);
  private static final org.apache.thrift.protocol.TField BOOK_FIELD_DESC = new org.apache.thrift.protocol.TField("book", org.apache.thrift.protocol.TType.STRUCT, (short)3);
  private static final org.apache.thrift.protocol.TField ADVERT_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("advertType", org.apache.thrift.protocol.TType.I32, (short)4);

  private static final Map<Class<? extends IScheme>, SchemeFactory> schemes = new HashMap<Class<? extends IScheme>, SchemeFactory>();
  static {
    schemes.put(StandardScheme.class, new AdvertStandardSchemeFactory());
    schemes.put(TupleScheme.class, new AdvertTupleSchemeFactory());
  }

  public int id; // required
  public int issuer; // required
  public com.booktion.thrift.Book book; // required
  /**
   * 
   * @see com.booktion.thrift.AdvertType
   */
  public com.booktion.thrift.AdvertType advertType; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    ID((short)1, "id"),
    ISSUER((short)2, "issuer"),
    BOOK((short)3, "book"),
    /**
     * 
     * @see com.booktion.thrift.AdvertType
     */
    ADVERT_TYPE((short)4, "advertType");

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
        case 1: // ID
          return ID;
        case 2: // ISSUER
          return ISSUER;
        case 3: // BOOK
          return BOOK;
        case 4: // ADVERT_TYPE
          return ADVERT_TYPE;
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
  private static final int __ID_ISSET_ID = 0;
  private static final int __ISSUER_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.ID, new org.apache.thrift.meta_data.FieldMetaData("id", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.ISSUER, new org.apache.thrift.meta_data.FieldMetaData("issuer", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I32)));
    tmpMap.put(_Fields.BOOK, new org.apache.thrift.meta_data.FieldMetaData("book", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.StructMetaData(org.apache.thrift.protocol.TType.STRUCT, com.booktion.thrift.Book.class)));
    tmpMap.put(_Fields.ADVERT_TYPE, new org.apache.thrift.meta_data.FieldMetaData("advertType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.EnumMetaData(org.apache.thrift.protocol.TType.ENUM, com.booktion.thrift.AdvertType.class)));
    metaDataMap = Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(Advert.class, metaDataMap);
  }

  public Advert() {
  }

  public Advert(
    int id,
    int issuer,
    com.booktion.thrift.Book book,
    com.booktion.thrift.AdvertType advertType)
  {
    this();
    this.id = id;
    setIdIsSet(true);
    this.issuer = issuer;
    setIssuerIsSet(true);
    this.book = book;
    this.advertType = advertType;
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public Advert(Advert other) {
    __isset_bitfield = other.__isset_bitfield;
    this.id = other.id;
    this.issuer = other.issuer;
    if (other.isSetBook()) {
      this.book = new com.booktion.thrift.Book(other.book);
    }
    if (other.isSetAdvertType()) {
      this.advertType = other.advertType;
    }
  }

  public Advert deepCopy() {
    return new Advert(this);
  }

  @Override
  public void clear() {
    setIdIsSet(false);
    this.id = 0;
    setIssuerIsSet(false);
    this.issuer = 0;
    this.book = null;
    this.advertType = null;
  }

  public int getId() {
    return this.id;
  }

  public Advert setId(int id) {
    this.id = id;
    setIdIsSet(true);
    return this;
  }

  public void unsetId() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ID_ISSET_ID);
  }

  /** Returns true if field id is set (has been assigned a value) and false otherwise */
  public boolean isSetId() {
    return EncodingUtils.testBit(__isset_bitfield, __ID_ISSET_ID);
  }

  public void setIdIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ID_ISSET_ID, value);
  }

  public int getIssuer() {
    return this.issuer;
  }

  public Advert setIssuer(int issuer) {
    this.issuer = issuer;
    setIssuerIsSet(true);
    return this;
  }

  public void unsetIssuer() {
    __isset_bitfield = EncodingUtils.clearBit(__isset_bitfield, __ISSUER_ISSET_ID);
  }

  /** Returns true if field issuer is set (has been assigned a value) and false otherwise */
  public boolean isSetIssuer() {
    return EncodingUtils.testBit(__isset_bitfield, __ISSUER_ISSET_ID);
  }

  public void setIssuerIsSet(boolean value) {
    __isset_bitfield = EncodingUtils.setBit(__isset_bitfield, __ISSUER_ISSET_ID, value);
  }

  public com.booktion.thrift.Book getBook() {
    return this.book;
  }

  public Advert setBook(com.booktion.thrift.Book book) {
    this.book = book;
    return this;
  }

  public void unsetBook() {
    this.book = null;
  }

  /** Returns true if field book is set (has been assigned a value) and false otherwise */
  public boolean isSetBook() {
    return this.book != null;
  }

  public void setBookIsSet(boolean value) {
    if (!value) {
      this.book = null;
    }
  }

  /**
   * 
   * @see com.booktion.thrift.AdvertType
   */
  public com.booktion.thrift.AdvertType getAdvertType() {
    return this.advertType;
  }

  /**
   * 
   * @see com.booktion.thrift.AdvertType
   */
  public Advert setAdvertType(com.booktion.thrift.AdvertType advertType) {
    this.advertType = advertType;
    return this;
  }

  public void unsetAdvertType() {
    this.advertType = null;
  }

  /** Returns true if field advertType is set (has been assigned a value) and false otherwise */
  public boolean isSetAdvertType() {
    return this.advertType != null;
  }

  public void setAdvertTypeIsSet(boolean value) {
    if (!value) {
      this.advertType = null;
    }
  }

  public void setFieldValue(_Fields field, Object value) {
    switch (field) {
    case ID:
      if (value == null) {
        unsetId();
      } else {
        setId((Integer)value);
      }
      break;

    case ISSUER:
      if (value == null) {
        unsetIssuer();
      } else {
        setIssuer((Integer)value);
      }
      break;

    case BOOK:
      if (value == null) {
        unsetBook();
      } else {
        setBook((com.booktion.thrift.Book)value);
      }
      break;

    case ADVERT_TYPE:
      if (value == null) {
        unsetAdvertType();
      } else {
        setAdvertType((com.booktion.thrift.AdvertType)value);
      }
      break;

    }
  }

  public Object getFieldValue(_Fields field) {
    switch (field) {
    case ID:
      return Integer.valueOf(getId());

    case ISSUER:
      return Integer.valueOf(getIssuer());

    case BOOK:
      return getBook();

    case ADVERT_TYPE:
      return getAdvertType();

    }
    throw new IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new IllegalArgumentException();
    }

    switch (field) {
    case ID:
      return isSetId();
    case ISSUER:
      return isSetIssuer();
    case BOOK:
      return isSetBook();
    case ADVERT_TYPE:
      return isSetAdvertType();
    }
    throw new IllegalStateException();
  }

  @Override
  public boolean equals(Object that) {
    if (that == null)
      return false;
    if (that instanceof Advert)
      return this.equals((Advert)that);
    return false;
  }

  public boolean equals(Advert that) {
    if (that == null)
      return false;

    boolean this_present_id = true;
    boolean that_present_id = true;
    if (this_present_id || that_present_id) {
      if (!(this_present_id && that_present_id))
        return false;
      if (this.id != that.id)
        return false;
    }

    boolean this_present_issuer = true;
    boolean that_present_issuer = true;
    if (this_present_issuer || that_present_issuer) {
      if (!(this_present_issuer && that_present_issuer))
        return false;
      if (this.issuer != that.issuer)
        return false;
    }

    boolean this_present_book = true && this.isSetBook();
    boolean that_present_book = true && that.isSetBook();
    if (this_present_book || that_present_book) {
      if (!(this_present_book && that_present_book))
        return false;
      if (!this.book.equals(that.book))
        return false;
    }

    boolean this_present_advertType = true && this.isSetAdvertType();
    boolean that_present_advertType = true && that.isSetAdvertType();
    if (this_present_advertType || that_present_advertType) {
      if (!(this_present_advertType && that_present_advertType))
        return false;
      if (!this.advertType.equals(that.advertType))
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    return 0;
  }

  @Override
  public int compareTo(Advert other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = Boolean.valueOf(isSetId()).compareTo(other.isSetId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.id, other.id);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetIssuer()).compareTo(other.isSetIssuer());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetIssuer()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.issuer, other.issuer);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetBook()).compareTo(other.isSetBook());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetBook()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.book, other.book);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = Boolean.valueOf(isSetAdvertType()).compareTo(other.isSetAdvertType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetAdvertType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.advertType, other.advertType);
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
    StringBuilder sb = new StringBuilder("Advert(");
    boolean first = true;

    sb.append("id:");
    sb.append(this.id);
    first = false;
    if (!first) sb.append(", ");
    sb.append("issuer:");
    sb.append(this.issuer);
    first = false;
    if (!first) sb.append(", ");
    sb.append("book:");
    if (this.book == null) {
      sb.append("null");
    } else {
      sb.append(this.book);
    }
    first = false;
    if (!first) sb.append(", ");
    sb.append("advertType:");
    if (this.advertType == null) {
      sb.append("null");
    } else {
      sb.append(this.advertType);
    }
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
    if (book != null) {
      book.validate();
    }
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

  private static class AdvertStandardSchemeFactory implements SchemeFactory {
    public AdvertStandardScheme getScheme() {
      return new AdvertStandardScheme();
    }
  }

  private static class AdvertStandardScheme extends StandardScheme<Advert> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, Advert struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.id = iprot.readI32();
              struct.setIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // ISSUER
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.issuer = iprot.readI32();
              struct.setIssuerIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 3: // BOOK
            if (schemeField.type == org.apache.thrift.protocol.TType.STRUCT) {
              struct.book = new com.booktion.thrift.Book();
              struct.book.read(iprot);
              struct.setBookIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 4: // ADVERT_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I32) {
              struct.advertType = com.booktion.thrift.AdvertType.findByValue(iprot.readI32());
              struct.setAdvertTypeIsSet(true);
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
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, Advert struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(ID_FIELD_DESC);
      oprot.writeI32(struct.id);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(ISSUER_FIELD_DESC);
      oprot.writeI32(struct.issuer);
      oprot.writeFieldEnd();
      if (struct.book != null) {
        oprot.writeFieldBegin(BOOK_FIELD_DESC);
        struct.book.write(oprot);
        oprot.writeFieldEnd();
      }
      if (struct.advertType != null) {
        oprot.writeFieldBegin(ADVERT_TYPE_FIELD_DESC);
        oprot.writeI32(struct.advertType.getValue());
        oprot.writeFieldEnd();
      }
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class AdvertTupleSchemeFactory implements SchemeFactory {
    public AdvertTupleScheme getScheme() {
      return new AdvertTupleScheme();
    }
  }

  private static class AdvertTupleScheme extends TupleScheme<Advert> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, Advert struct) throws org.apache.thrift.TException {
      TTupleProtocol oprot = (TTupleProtocol) prot;
      BitSet optionals = new BitSet();
      if (struct.isSetId()) {
        optionals.set(0);
      }
      if (struct.isSetIssuer()) {
        optionals.set(1);
      }
      if (struct.isSetBook()) {
        optionals.set(2);
      }
      if (struct.isSetAdvertType()) {
        optionals.set(3);
      }
      oprot.writeBitSet(optionals, 4);
      if (struct.isSetId()) {
        oprot.writeI32(struct.id);
      }
      if (struct.isSetIssuer()) {
        oprot.writeI32(struct.issuer);
      }
      if (struct.isSetBook()) {
        struct.book.write(oprot);
      }
      if (struct.isSetAdvertType()) {
        oprot.writeI32(struct.advertType.getValue());
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, Advert struct) throws org.apache.thrift.TException {
      TTupleProtocol iprot = (TTupleProtocol) prot;
      BitSet incoming = iprot.readBitSet(4);
      if (incoming.get(0)) {
        struct.id = iprot.readI32();
        struct.setIdIsSet(true);
      }
      if (incoming.get(1)) {
        struct.issuer = iprot.readI32();
        struct.setIssuerIsSet(true);
      }
      if (incoming.get(2)) {
        struct.book = new com.booktion.thrift.Book();
        struct.book.read(iprot);
        struct.setBookIsSet(true);
      }
      if (incoming.get(3)) {
        struct.advertType = com.booktion.thrift.AdvertType.findByValue(iprot.readI32());
        struct.setAdvertTypeIsSet(true);
      }
    }
  }

}

