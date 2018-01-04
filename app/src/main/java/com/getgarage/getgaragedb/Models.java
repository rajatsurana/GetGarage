package com.getgarage.getgaragedb;

/**
 * Created by Rajat on 23-10-2015.
 */
public class Models {
    int modelBrandId;
    int modelId;
    String modelName;
    public Models(String modelName){
        this.modelName=modelName;
    }

    public int getmodelBrandId() {
        return modelBrandId;
    }

    public int getModelId() {
        return modelId;
    }

    public String getModelName() {
        return modelName;
    }

    public void setmodelBrandId(int modelBrandId) {
        this.modelBrandId = modelBrandId;
    }

    public void setModelId(int modelId) {
        this.modelId = modelId;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }
}
