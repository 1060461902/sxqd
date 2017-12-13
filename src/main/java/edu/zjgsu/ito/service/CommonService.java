package edu.zjgsu.ito.service;

import edu.zjgsu.ito.model.Weight;

public interface CommonService {

    Integer role2user(Integer roleid, Integer id);

    Integer setWeight(Weight weight);
}
