package com.bitter.esb.model;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
public class ESBResponse {

    private List<Result> resultList;

    private Object localHead;

    private Object appHead;

    public void addResult(String errorCode,String errorMessage){
        if(resultList == null){
            resultList = new ArrayList<Result>();
        }

        resultList.add(new Result(errorCode,errorMessage));
    }

    public String getErrorCode(int index){
        if(resultList == null)
            return null;

        if(resultList.size() <= index)
            return null;

        return resultList.get(index).errorCode;
    }

    public String getErrorMessage(int index){
        if(resultList == null)
            return null;

        if(resultList.size() <= index)
            return null;

        return resultList.get(index).errorMessage;
    }

    public String getErrorCode(){
        return getErrorCode(0);
    }

    public String getErrorMessage(){
        return getErrorMessage(0);
    }

    public int resultSize(){
        if(resultList == null){
            return 0;
        }
        else
            return resultList.size();

    }



    public Object getLocalHead() {
        return localHead;
    }

    public void setLocalHead(Object localHead) {
        this.localHead = localHead;
    }

    public Object getAppHead() {
        return appHead;
    }

    public void setAppHead(Object appHead) {
        this.appHead = appHead;
    }



    class Result{
        private String errorCode;
        private String errorMessage;

        public Result(String errorCode,String errorMessage){
            this.errorCode = errorCode;
            if (null == errorMessage || "".equals(errorMessage.trim())) {
                this.errorMessage = "-系统异常-";
            } else {
                this.errorMessage = errorMessage;
            }
        }
    }

}

