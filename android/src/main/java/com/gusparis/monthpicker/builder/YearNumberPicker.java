package com.gusparis.monthpicker.builder;

import android.widget.NumberPicker;

class YearNumberPicker extends MonthYearNumberPicker {

  private static final int DEFAULT_SIZE = 204;

  @Override
  YearNumberPicker onScrollListener(MonthYearScrollListener scrollListener) {
    yearPicker.setOnScrollListener(scrollListener);
    yearPicker.setOnValueChangedListener(scrollListener);
    return this;
  }

  @Override
  YearNumberPicker build() {
    int year = props.value().getYear();
    yearPicker.setMinValue(year - DEFAULT_SIZE);
    yearPicker.setMaxValue(year + DEFAULT_SIZE);
    yearPicker.setFormatter(new JpYear());
    yearPicker.setValue(year);
    return this;
  }

  @Override
  synchronized void setValue() {
    int year = yearPicker.getValue();
    int value = year;
    if (props.minimumValue() != null && year < props.minimumValue().getYear()) {
      value = props.minimumValue().getYear();
    } else if (props.maximumValue() != null && year > props.maximumValue().getYear()) {
      value = props.maximumValue().getYear();
    }
    yearPicker.setValue(value);
  }

  @Override
  int getValue() {
    return yearPicker.getValue();
  }

  private static class JpYear implements NumberPicker.Formatter {
    @Override
    public String format(int i) {
      return String.format("%d年", i);
    }
  }
}
