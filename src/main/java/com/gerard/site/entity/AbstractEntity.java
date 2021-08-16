package com.gerard.site.entity;

import java.util.Objects;

public class AbstractEntity<K>{
    protected K id;

    public AbstractEntity(){
    }

    public final K getId(){
        return id;
    }

    public void setId(K id){
        this.id = id;
    }

    @Override
    public boolean equals(Object object){
        if(this == object){
            return true;
        }
        if(object instanceof AbstractEntity<?> abstractEntity){
          return abstractEntity.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int hashCode(){
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
