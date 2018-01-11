package edu.zjgsu.ito.vo;

public class IdVo {
    String[] id;
    Integer[] integerId;

    public String[] getId() {
        return id;
    }

    public void setId(String[] id) {
        this.id = id;
    }

    public Integer[] String2Integer(String[] id) {
        Integer[] temp = new Integer[id.length];

        for (int i = 0; i < id.length; i++) {
            temp[i] = Integer.parseInt(id[i]);
        }

        setIntegerId(temp);
        return getIntegerId();
    }

    public Integer[] getIntegerId() {
        return integerId;
    }

    public void setIntegerId(Integer[] integerId) {
        this.integerId = integerId;
    }
}
