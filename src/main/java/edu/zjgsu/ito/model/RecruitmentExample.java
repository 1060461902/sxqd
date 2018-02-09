package edu.zjgsu.ito.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecruitmentExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public RecruitmentExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getOffset() {
        return offset;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("company_id is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("company_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(Integer value) {
            addCriterion("company_id =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(Integer value) {
            addCriterion("company_id <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(Integer value) {
            addCriterion("company_id >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("company_id >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(Integer value) {
            addCriterion("company_id <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(Integer value) {
            addCriterion("company_id <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<Integer> values) {
            addCriterion("company_id in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<Integer> values) {
            addCriterion("company_id not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(Integer value1, Integer value2) {
            addCriterion("company_id between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(Integer value1, Integer value2) {
            addCriterion("company_id not between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andPostIsNull() {
            addCriterion("post is null");
            return (Criteria) this;
        }

        public Criteria andPostIsNotNull() {
            addCriterion("post is not null");
            return (Criteria) this;
        }

        public Criteria andPostEqualTo(String value) {
            addCriterion("post =", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotEqualTo(String value) {
            addCriterion("post <>", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThan(String value) {
            addCriterion("post >", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostGreaterThanOrEqualTo(String value) {
            addCriterion("post >=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThan(String value) {
            addCriterion("post <", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLessThanOrEqualTo(String value) {
            addCriterion("post <=", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostLike(String value) {
            addCriterion("post like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotLike(String value) {
            addCriterion("post not like", value, "post");
            return (Criteria) this;
        }

        public Criteria andPostIn(List<String> values) {
            addCriterion("post in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotIn(List<String> values) {
            addCriterion("post not in", values, "post");
            return (Criteria) this;
        }

        public Criteria andPostBetween(String value1, String value2) {
            addCriterion("post between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andPostNotBetween(String value1, String value2) {
            addCriterion("post not between", value1, value2, "post");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberIsNull() {
            addCriterion("current_number is null");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberIsNotNull() {
            addCriterion("current_number is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberEqualTo(Integer value) {
            addCriterion("current_number =", value, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberNotEqualTo(Integer value) {
            addCriterion("current_number <>", value, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberGreaterThan(Integer value) {
            addCriterion("current_number >", value, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("current_number >=", value, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberLessThan(Integer value) {
            addCriterion("current_number <", value, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberLessThanOrEqualTo(Integer value) {
            addCriterion("current_number <=", value, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberIn(List<Integer> values) {
            addCriterion("current_number in", values, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberNotIn(List<Integer> values) {
            addCriterion("current_number not in", values, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberBetween(Integer value1, Integer value2) {
            addCriterion("current_number between", value1, value2, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andCurrentNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("current_number not between", value1, value2, "currentNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNull() {
            addCriterion("total_number is null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIsNotNull() {
            addCriterion("total_number is not null");
            return (Criteria) this;
        }

        public Criteria andTotalNumberEqualTo(Integer value) {
            addCriterion("total_number =", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotEqualTo(Integer value) {
            addCriterion("total_number <>", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThan(Integer value) {
            addCriterion("total_number >", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_number >=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThan(Integer value) {
            addCriterion("total_number <", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberLessThanOrEqualTo(Integer value) {
            addCriterion("total_number <=", value, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberIn(List<Integer> values) {
            addCriterion("total_number in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotIn(List<Integer> values) {
            addCriterion("total_number not in", values, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberBetween(Integer value1, Integer value2) {
            addCriterion("total_number between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andTotalNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("total_number not between", value1, value2, "totalNumber");
            return (Criteria) this;
        }

        public Criteria andPostInfoIsNull() {
            addCriterion("post_info is null");
            return (Criteria) this;
        }

        public Criteria andPostInfoIsNotNull() {
            addCriterion("post_info is not null");
            return (Criteria) this;
        }

        public Criteria andPostInfoEqualTo(String value) {
            addCriterion("post_info =", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoNotEqualTo(String value) {
            addCriterion("post_info <>", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoGreaterThan(String value) {
            addCriterion("post_info >", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoGreaterThanOrEqualTo(String value) {
            addCriterion("post_info >=", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoLessThan(String value) {
            addCriterion("post_info <", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoLessThanOrEqualTo(String value) {
            addCriterion("post_info <=", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoLike(String value) {
            addCriterion("post_info like", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoNotLike(String value) {
            addCriterion("post_info not like", value, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoIn(List<String> values) {
            addCriterion("post_info in", values, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoNotIn(List<String> values) {
            addCriterion("post_info not in", values, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoBetween(String value1, String value2) {
            addCriterion("post_info between", value1, value2, "postInfo");
            return (Criteria) this;
        }

        public Criteria andPostInfoNotBetween(String value1, String value2) {
            addCriterion("post_info not between", value1, value2, "postInfo");
            return (Criteria) this;
        }

        public Criteria andCheckedIsNull() {
            addCriterion("checked is null");
            return (Criteria) this;
        }

        public Criteria andCheckedIsNotNull() {
            addCriterion("checked is not null");
            return (Criteria) this;
        }

        public Criteria andCheckedEqualTo(Boolean value) {
            addCriterion("checked =", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotEqualTo(Boolean value) {
            addCriterion("checked <>", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedGreaterThan(Boolean value) {
            addCriterion("checked >", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("checked >=", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedLessThan(Boolean value) {
            addCriterion("checked <", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedLessThanOrEqualTo(Boolean value) {
            addCriterion("checked <=", value, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedIn(List<Boolean> values) {
            addCriterion("checked in", values, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotIn(List<Boolean> values) {
            addCriterion("checked not in", values, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedBetween(Boolean value1, Boolean value2) {
            addCriterion("checked between", value1, value2, "checked");
            return (Criteria) this;
        }

        public Criteria andCheckedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("checked not between", value1, value2, "checked");
            return (Criteria) this;
        }

        public Criteria andPassIsNull() {
            addCriterion("pass is null");
            return (Criteria) this;
        }

        public Criteria andPassIsNotNull() {
            addCriterion("pass is not null");
            return (Criteria) this;
        }

        public Criteria andPassEqualTo(Boolean value) {
            addCriterion("pass =", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassNotEqualTo(Boolean value) {
            addCriterion("pass <>", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassGreaterThan(Boolean value) {
            addCriterion("pass >", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassGreaterThanOrEqualTo(Boolean value) {
            addCriterion("pass >=", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassLessThan(Boolean value) {
            addCriterion("pass <", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassLessThanOrEqualTo(Boolean value) {
            addCriterion("pass <=", value, "pass");
            return (Criteria) this;
        }

        public Criteria andPassIn(List<Boolean> values) {
            addCriterion("pass in", values, "pass");
            return (Criteria) this;
        }

        public Criteria andPassNotIn(List<Boolean> values) {
            addCriterion("pass not in", values, "pass");
            return (Criteria) this;
        }

        public Criteria andPassBetween(Boolean value1, Boolean value2) {
            addCriterion("pass between", value1, value2, "pass");
            return (Criteria) this;
        }

        public Criteria andPassNotBetween(Boolean value1, Boolean value2) {
            addCriterion("pass not between", value1, value2, "pass");
            return (Criteria) this;
        }

        public Criteria andDeleteTagIsNull() {
            addCriterion("delete_tag is null");
            return (Criteria) this;
        }

        public Criteria andDeleteTagIsNotNull() {
            addCriterion("delete_tag is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteTagEqualTo(Boolean value) {
            addCriterion("delete_tag =", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagNotEqualTo(Boolean value) {
            addCriterion("delete_tag <>", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagGreaterThan(Boolean value) {
            addCriterion("delete_tag >", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagGreaterThanOrEqualTo(Boolean value) {
            addCriterion("delete_tag >=", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagLessThan(Boolean value) {
            addCriterion("delete_tag <", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagLessThanOrEqualTo(Boolean value) {
            addCriterion("delete_tag <=", value, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagIn(List<Boolean> values) {
            addCriterion("delete_tag in", values, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagNotIn(List<Boolean> values) {
            addCriterion("delete_tag not in", values, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagBetween(Boolean value1, Boolean value2) {
            addCriterion("delete_tag between", value1, value2, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andDeleteTagNotBetween(Boolean value1, Boolean value2) {
            addCriterion("delete_tag not between", value1, value2, "deleteTag");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andSkillRequireIsNull() {
            addCriterion("skill_require is null");
            return (Criteria) this;
        }

        public Criteria andSkillRequireIsNotNull() {
            addCriterion("skill_require is not null");
            return (Criteria) this;
        }

        public Criteria andSkillRequireEqualTo(String value) {
            addCriterion("skill_require =", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireNotEqualTo(String value) {
            addCriterion("skill_require <>", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireGreaterThan(String value) {
            addCriterion("skill_require >", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireGreaterThanOrEqualTo(String value) {
            addCriterion("skill_require >=", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireLessThan(String value) {
            addCriterion("skill_require <", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireLessThanOrEqualTo(String value) {
            addCriterion("skill_require <=", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireLike(String value) {
            addCriterion("skill_require like", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireNotLike(String value) {
            addCriterion("skill_require not like", value, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireIn(List<String> values) {
            addCriterion("skill_require in", values, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireNotIn(List<String> values) {
            addCriterion("skill_require not in", values, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireBetween(String value1, String value2) {
            addCriterion("skill_require between", value1, value2, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andSkillRequireNotBetween(String value1, String value2) {
            addCriterion("skill_require not between", value1, value2, "skillRequire");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNull() {
            addCriterion("post_time is null");
            return (Criteria) this;
        }

        public Criteria andPostTimeIsNotNull() {
            addCriterion("post_time is not null");
            return (Criteria) this;
        }

        public Criteria andPostTimeEqualTo(String value) {
            addCriterion("post_time =", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotEqualTo(String value) {
            addCriterion("post_time <>", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThan(String value) {
            addCriterion("post_time >", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeGreaterThanOrEqualTo(String value) {
            addCriterion("post_time >=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThan(String value) {
            addCriterion("post_time <", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLessThanOrEqualTo(String value) {
            addCriterion("post_time <=", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeLike(String value) {
            addCriterion("post_time like", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotLike(String value) {
            addCriterion("post_time not like", value, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeIn(List<String> values) {
            addCriterion("post_time in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotIn(List<String> values) {
            addCriterion("post_time not in", values, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeBetween(String value1, String value2) {
            addCriterion("post_time between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andPostTimeNotBetween(String value1, String value2) {
            addCriterion("post_time not between", value1, value2, "postTime");
            return (Criteria) this;
        }

        public Criteria andRemoveIsNull() {
            addCriterion("remove is null");
            return (Criteria) this;
        }

        public Criteria andRemoveIsNotNull() {
            addCriterion("remove is not null");
            return (Criteria) this;
        }

        public Criteria andRemoveEqualTo(Boolean value) {
            addCriterion("remove =", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveNotEqualTo(Boolean value) {
            addCriterion("remove <>", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveGreaterThan(Boolean value) {
            addCriterion("remove >", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveGreaterThanOrEqualTo(Boolean value) {
            addCriterion("remove >=", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveLessThan(Boolean value) {
            addCriterion("remove <", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveLessThanOrEqualTo(Boolean value) {
            addCriterion("remove <=", value, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveIn(List<Boolean> values) {
            addCriterion("remove in", values, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveNotIn(List<Boolean> values) {
            addCriterion("remove not in", values, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveBetween(Boolean value1, Boolean value2) {
            addCriterion("remove between", value1, value2, "remove");
            return (Criteria) this;
        }

        public Criteria andRemoveNotBetween(Boolean value1, Boolean value2) {
            addCriterion("remove not between", value1, value2, "remove");
            return (Criteria) this;
        }

        public Criteria andForbiddenIsNull() {
            addCriterion("forbidden is null");
            return (Criteria) this;
        }

        public Criteria andForbiddenIsNotNull() {
            addCriterion("forbidden is not null");
            return (Criteria) this;
        }

        public Criteria andForbiddenEqualTo(Boolean value) {
            addCriterion("forbidden =", value, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenNotEqualTo(Boolean value) {
            addCriterion("forbidden <>", value, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenGreaterThan(Boolean value) {
            addCriterion("forbidden >", value, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenGreaterThanOrEqualTo(Boolean value) {
            addCriterion("forbidden >=", value, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenLessThan(Boolean value) {
            addCriterion("forbidden <", value, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenLessThanOrEqualTo(Boolean value) {
            addCriterion("forbidden <=", value, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenIn(List<Boolean> values) {
            addCriterion("forbidden in", values, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenNotIn(List<Boolean> values) {
            addCriterion("forbidden not in", values, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenBetween(Boolean value1, Boolean value2) {
            addCriterion("forbidden between", value1, value2, "forbidden");
            return (Criteria) this;
        }

        public Criteria andForbiddenNotBetween(Boolean value1, Boolean value2) {
            addCriterion("forbidden not between", value1, value2, "forbidden");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNull() {
            addCriterion("salary is null");
            return (Criteria) this;
        }

        public Criteria andSalaryIsNotNull() {
            addCriterion("salary is not null");
            return (Criteria) this;
        }

        public Criteria andSalaryEqualTo(String value) {
            addCriterion("salary =", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotEqualTo(String value) {
            addCriterion("salary <>", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThan(String value) {
            addCriterion("salary >", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryGreaterThanOrEqualTo(String value) {
            addCriterion("salary >=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThan(String value) {
            addCriterion("salary <", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLessThanOrEqualTo(String value) {
            addCriterion("salary <=", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryLike(String value) {
            addCriterion("salary like", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotLike(String value) {
            addCriterion("salary not like", value, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryIn(List<String> values) {
            addCriterion("salary in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotIn(List<String> values) {
            addCriterion("salary not in", values, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryBetween(String value1, String value2) {
            addCriterion("salary between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andSalaryNotBetween(String value1, String value2) {
            addCriterion("salary not between", value1, value2, "salary");
            return (Criteria) this;
        }

        public Criteria andContactIsNull() {
            addCriterion("contact is null");
            return (Criteria) this;
        }

        public Criteria andContactIsNotNull() {
            addCriterion("contact is not null");
            return (Criteria) this;
        }

        public Criteria andContactEqualTo(String value) {
            addCriterion("contact =", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotEqualTo(String value) {
            addCriterion("contact <>", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThan(String value) {
            addCriterion("contact >", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactGreaterThanOrEqualTo(String value) {
            addCriterion("contact >=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThan(String value) {
            addCriterion("contact <", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLessThanOrEqualTo(String value) {
            addCriterion("contact <=", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactLike(String value) {
            addCriterion("contact like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotLike(String value) {
            addCriterion("contact not like", value, "contact");
            return (Criteria) this;
        }

        public Criteria andContactIn(List<String> values) {
            addCriterion("contact in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotIn(List<String> values) {
            addCriterion("contact not in", values, "contact");
            return (Criteria) this;
        }

        public Criteria andContactBetween(String value1, String value2) {
            addCriterion("contact between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andContactNotBetween(String value1, String value2) {
            addCriterion("contact not between", value1, value2, "contact");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIsNull() {
            addCriterion("recruitment is null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIsNotNull() {
            addCriterion("recruitment is not null");
            return (Criteria) this;
        }

        public Criteria andRecruitmentEqualTo(Boolean value) {
            addCriterion("recruitment =", value, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentNotEqualTo(Boolean value) {
            addCriterion("recruitment <>", value, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentGreaterThan(Boolean value) {
            addCriterion("recruitment >", value, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentGreaterThanOrEqualTo(Boolean value) {
            addCriterion("recruitment >=", value, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentLessThan(Boolean value) {
            addCriterion("recruitment <", value, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentLessThanOrEqualTo(Boolean value) {
            addCriterion("recruitment <=", value, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentIn(List<Boolean> values) {
            addCriterion("recruitment in", values, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentNotIn(List<Boolean> values) {
            addCriterion("recruitment not in", values, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentBetween(Boolean value1, Boolean value2) {
            addCriterion("recruitment between", value1, value2, "recruitment");
            return (Criteria) this;
        }

        public Criteria andRecruitmentNotBetween(Boolean value1, Boolean value2) {
            addCriterion("recruitment not between", value1, value2, "recruitment");
            return (Criteria) this;
        }

        public Criteria andWhyIsNull() {
            addCriterion("why is null");
            return (Criteria) this;
        }

        public Criteria andWhyIsNotNull() {
            addCriterion("why is not null");
            return (Criteria) this;
        }

        public Criteria andWhyEqualTo(String value) {
            addCriterion("why =", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyNotEqualTo(String value) {
            addCriterion("why <>", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyGreaterThan(String value) {
            addCriterion("why >", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyGreaterThanOrEqualTo(String value) {
            addCriterion("why >=", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyLessThan(String value) {
            addCriterion("why <", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyLessThanOrEqualTo(String value) {
            addCriterion("why <=", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyLike(String value) {
            addCriterion("why like", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyNotLike(String value) {
            addCriterion("why not like", value, "why");
            return (Criteria) this;
        }

        public Criteria andWhyIn(List<String> values) {
            addCriterion("why in", values, "why");
            return (Criteria) this;
        }

        public Criteria andWhyNotIn(List<String> values) {
            addCriterion("why not in", values, "why");
            return (Criteria) this;
        }

        public Criteria andWhyBetween(String value1, String value2) {
            addCriterion("why between", value1, value2, "why");
            return (Criteria) this;
        }

        public Criteria andWhyNotBetween(String value1, String value2) {
            addCriterion("why not between", value1, value2, "why");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNull() {
            addCriterion("endTime is null");
            return (Criteria) this;
        }

        public Criteria andEndtimeIsNotNull() {
            addCriterion("endTime is not null");
            return (Criteria) this;
        }

        public Criteria andEndtimeEqualTo(Date value) {
            addCriterion("endTime =", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotEqualTo(Date value) {
            addCriterion("endTime <>", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThan(Date value) {
            addCriterion("endTime >", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("endTime >=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThan(Date value) {
            addCriterion("endTime <", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeLessThanOrEqualTo(Date value) {
            addCriterion("endTime <=", value, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeIn(List<Date> values) {
            addCriterion("endTime in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotIn(List<Date> values) {
            addCriterion("endTime not in", values, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeBetween(Date value1, Date value2) {
            addCriterion("endTime between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andEndtimeNotBetween(Date value1, Date value2) {
            addCriterion("endTime not between", value1, value2, "endtime");
            return (Criteria) this;
        }

        public Criteria andCityIsNull() {
            addCriterion("city is null");
            return (Criteria) this;
        }

        public Criteria andCityIsNotNull() {
            addCriterion("city is not null");
            return (Criteria) this;
        }

        public Criteria andCityEqualTo(String value) {
            addCriterion("city =", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotEqualTo(String value) {
            addCriterion("city <>", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThan(String value) {
            addCriterion("city >", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityGreaterThanOrEqualTo(String value) {
            addCriterion("city >=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThan(String value) {
            addCriterion("city <", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLessThanOrEqualTo(String value) {
            addCriterion("city <=", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityLike(String value) {
            addCriterion("city like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotLike(String value) {
            addCriterion("city not like", value, "city");
            return (Criteria) this;
        }

        public Criteria andCityIn(List<String> values) {
            addCriterion("city in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotIn(List<String> values) {
            addCriterion("city not in", values, "city");
            return (Criteria) this;
        }

        public Criteria andCityBetween(String value1, String value2) {
            addCriterion("city between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andCityNotBetween(String value1, String value2) {
            addCriterion("city not between", value1, value2, "city");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNull() {
            addCriterion("release_time is null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIsNotNull() {
            addCriterion("release_time is not null");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeEqualTo(String value) {
            addCriterion("release_time =", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotEqualTo(String value) {
            addCriterion("release_time <>", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThan(String value) {
            addCriterion("release_time >", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeGreaterThanOrEqualTo(String value) {
            addCriterion("release_time >=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThan(String value) {
            addCriterion("release_time <", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLessThanOrEqualTo(String value) {
            addCriterion("release_time <=", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeLike(String value) {
            addCriterion("release_time like", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotLike(String value) {
            addCriterion("release_time not like", value, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeIn(List<String> values) {
            addCriterion("release_time in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotIn(List<String> values) {
            addCriterion("release_time not in", values, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeBetween(String value1, String value2) {
            addCriterion("release_time between", value1, value2, "releaseTime");
            return (Criteria) this;
        }

        public Criteria andReleaseTimeNotBetween(String value1, String value2) {
            addCriterion("release_time not between", value1, value2, "releaseTime");
            return (Criteria) this;
        }
    }

    /**
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}