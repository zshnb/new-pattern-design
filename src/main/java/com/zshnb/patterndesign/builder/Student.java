package com.zshnb.patterndesign.builder;

import java.lang.String;

public class Student {
  private int id = 0;

  private String name = "";

  private Student() {
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public static Builder newBuilder() {
    return new Builder(new Student());
  }

  public Builder toBuilder() {
    return new Builder(this);
  }

  public static class Builder {
    private Student student;

    private Builder(Student student) {
      this.student = student;
    }

    public Builder setId(int id) {
      student.id = id;
      return this;
    }

    public Builder clearId() {
      student.id = 0;
      return this;
    }

    public Builder setName(String name) {
      student.name = name;
      return this;
    }

    public Builder clearName() {
      student.name = "";
      return this;
    }

    public Student build() {
      return student;
    }
  }
}
