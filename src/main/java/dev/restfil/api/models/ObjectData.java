package dev.restfil.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
@Setter
public class ObjectData {
     private int year;
     private double price;

     @JsonProperty("CPU model")
     private String cpuModel;

     @JsonProperty("Hard disk size")
     private String hardDiskSize;

    @Override
    public boolean equals(final Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public static ObjectData getFakeObjectData() {
        ObjectData data = new ObjectData();
        data.setPrice(1849.99);
        data.setYear(2019);
        data.setCpuModel("Intel Core i9");
        data.setHardDiskSize("1 TB");
        return data;
    }
}
