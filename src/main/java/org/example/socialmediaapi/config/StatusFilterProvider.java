package org.example.socialmediaapi.config;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.example.socialmediaapi.entity.BaseEntity;
import org.springframework.stereotype.Component;

@Component
public class StatusFilterProvider extends SimpleFilterProvider {
    public StatusFilterProvider() {
        this.addFilter("statusFilter", new SimpleBeanPropertyFilter() {
            @Override public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
                if (pojo instanceof BaseEntity) {
                    BaseEntity baseEntity = (BaseEntity) pojo;
                    if (baseEntity.getStatus() == 1) {
                        writer.serializeAsField(pojo, jgen, provider);
                    }
                    else {

                    }
                } else {
                    writer.serializeAsField(pojo, jgen, provider);
                }
            }
        });
    }
}