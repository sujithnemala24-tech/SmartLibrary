package com.smartlibrary.model;
public class User { private final String id,name; public User(String id,String name){if(id==null||id.isBlank()||name==null||name.isBlank())throw new IllegalArgumentException("User ID and name required");this.id=id;this.name=name;} public String getId(){return id;} public String getName(){return name;} }
