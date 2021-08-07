package com.team4.getvaxi.models;

import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HospitalByProvince {

    HashMap<String, Hospital> hopitalsByProvince;
}
