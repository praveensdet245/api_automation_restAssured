package dev.restfil.api.models;


import com.github.javafaker.Faker;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

@Getter
@Setter
public class ObjectClass {

    private String id;
    private String name;
    private ObjectData data;
    private Date createdAt;

    @Override
    public boolean equals(final Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

    public static ObjectClass generateFakeObject() {
        Faker faker = new Faker();
        ObjectClass objectClass = new ObjectClass();
        objectClass.setName(faker.random().hex());
        objectClass.setData(ObjectData.getFakeObjectData());
        return objectClass;
    }


}
