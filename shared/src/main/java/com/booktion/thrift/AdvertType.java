/**
 * Autogenerated by Thrift Compiler (0.9.1)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.booktion.thrift;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum AdvertType implements org.apache.thrift.TEnum {
  FIX_PRICE(0),
  AUCTION(1);

  private final int value;

  private AdvertType(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static AdvertType findByValue(int value) { 
    switch (value) {
      case 0:
        return FIX_PRICE;
      case 1:
        return AUCTION;
      default:
        return null;
    }
  }
}