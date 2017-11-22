package edu.zjgsu.ito.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecruitmentInfoExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecruitmentInfoExample() {
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

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNull() {
            addCriterion("companyID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyidIsNotNull() {
            addCriterion("companyID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyidEqualTo(String value) {
            addCriterion("companyID =", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotEqualTo(String value) {
            addCriterion("companyID <>", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThan(String value) {
            addCriterion("companyID >", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidGreaterThanOrEqualTo(String value) {
            addCriterion("companyID >=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThan(String value) {
            addCriterion("companyID <", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLessThanOrEqualTo(String value) {
            addCriterion("companyID <=", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidLike(String value) {
            addCriterion("companyID like", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotLike(String value) {
            addCriterion("companyID not like", value, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidIn(List<String> values) {
            addCriterion("companyID in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotIn(List<String> values) {
            addCriterion("companyID not in", values, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidBetween(String value1, String value2) {
            addCriterion("companyID between", value1, value2, "companyid");
            return (Criteria) this;
        }

        public Criteria andCompanyidNotBetween(String value1, String value2) {
            addCriterion("companyID not between", value1, value2, "companyid");
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

        public Criteria andCurrentnumberIsNull() {
            addCriterion("currentNumber is null");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberIsNotNull() {
            addCriterion("currentNumber is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberEqualTo(Integer value) {
            addCriterion("currentNumber =", value, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberNotEqualTo(Integer value) {
            addCriterion("currentNumber <>", value, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberGreaterThan(Integer value) {
            addCriterion("currentNumber >", value, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("currentNumber >=", value, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberLessThan(Integer value) {
            addCriterion("currentNumber <", value, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberLessThanOrEqualTo(Integer value) {
            addCriterion("currentNumber <=", value, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberIn(List<Integer> values) {
            addCriterion("currentNumber in", values, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberNotIn(List<Integer> values) {
            addCriterion("currentNumber not in", values, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberBetween(Integer value1, Integer value2) {
            addCriterion("currentNumber between", value1, value2, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andCurrentnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("currentNumber not between", value1, value2, "currentnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberIsNull() {
            addCriterion("totalNumber is null");
            return (Criteria) this;
        }

        public Criteria andTotalnumberIsNotNull() {
            addCriterion("totalNumber is not null");
            return (Criteria) this;
        }

        public Criteria andTotalnumberEqualTo(Integer value) {
            addCriterion("totalNumber =", value, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberNotEqualTo(Integer value) {
            addCriterion("totalNumber <>", value, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberGreaterThan(Integer value) {
            addCriterion("totalNumber >", value, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("totalNumber >=", value, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberLessThan(Integer value) {
            addCriterion("totalNumber <", value, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberLessThanOrEqualTo(Integer value) {
            addCriterion("totalNumber <=", value, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberIn(List<Integer> values) {
            addCriterion("totalNumber in", values, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberNotIn(List<Integer> values) {
            addCriterion("totalNumber not in", values, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberBetween(Integer value1, Integer value2) {
            addCriterion("totalNumber between", value1, value2, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andTotalnumberNotBetween(Integer value1, Integer value2) {
            addCriterion("totalNumber not between", value1, value2, "totalnumber");
            return (Criteria) this;
        }

        public Criteria andPostinfoIsNull() {
            addCriterion("postInfo is null");
            return (Criteria) this;
        }

        public Criteria andPostinfoIsNotNull() {
            addCriterion("postInfo is not null");
            return (Criteria) this;
        }

        public Criteria andPostinfoEqualTo(String value) {
            addCriterion("postInfo =", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoNotEqualTo(String value) {
            addCriterion("postInfo <>", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoGreaterThan(String value) {
            addCriterion("postInfo >", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoGreaterThanOrEqualTo(String value) {
            addCriterion("postInfo >=", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoLessThan(String value) {
            addCriterion("postInfo <", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoLessThanOrEqualTo(String value) {
            addCriterion("postInfo <=", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoLike(String value) {
            addCriterion("postInfo like", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoNotLike(String value) {
            addCriterion("postInfo not like", value, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoIn(List<String> values) {
            addCriterion("postInfo in", values, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoNotIn(List<String> values) {
            addCriterion("postInfo not in", values, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoBetween(String value1, String value2) {
            addCriterion("postInfo between", value1, value2, "postinfo");
            return (Criteria) this;
        }

        public Criteria andPostinfoNotBetween(String value1, String value2) {
            addCriterion("postInfo not between", value1, value2, "postinfo");
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

        public Criteria andDeleteIsNull() {
            addCriterion("delete is null");
            return (Criteria) this;
        }

        public Criteria andDeleteIsNotNull() {
            addCriterion("delete is not null");
            return (Criteria) this;
        }

        public Criteria andDeleteEqualTo(Boolean value) {
            addCriterion("delete =", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotEqualTo(Boolean value) {
            addCriterion("delete <>", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteGreaterThan(Boolean value) {
            addCriterion("delete >", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteGreaterThanOrEqualTo(Boolean value) {
            addCriterion("delete >=", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLessThan(Boolean value) {
            addCriterion("delete <", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteLessThanOrEqualTo(Boolean value) {
            addCriterion("delete <=", value, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteIn(List<Boolean> values) {
            addCriterion("delete in", values, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotIn(List<Boolean> values) {
            addCriterion("delete not in", values, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteBetween(Boolean value1, Boolean value2) {
            addCriterion("delete between", value1, value2, "delete");
            return (Criteria) this;
        }

        public Criteria andDeleteNotBetween(Boolean value1, Boolean value2) {
            addCriterion("delete not between", value1, value2, "delete");
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

        public Criteria andSkillrequirementIsNull() {
            addCriterion("skillRequirement is null");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementIsNotNull() {
            addCriterion("skillRequirement is not null");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementEqualTo(String value) {
            addCriterion("skillRequirement =", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementNotEqualTo(String value) {
            addCriterion("skillRequirement <>", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementGreaterThan(String value) {
            addCriterion("skillRequirement >", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementGreaterThanOrEqualTo(String value) {
            addCriterion("skillRequirement >=", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementLessThan(String value) {
            addCriterion("skillRequirement <", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementLessThanOrEqualTo(String value) {
            addCriterion("skillRequirement <=", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementLike(String value) {
            addCriterion("skillRequirement like", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementNotLike(String value) {
            addCriterion("skillRequirement not like", value, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementIn(List<String> values) {
            addCriterion("skillRequirement in", values, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementNotIn(List<String> values) {
            addCriterion("skillRequirement not in", values, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementBetween(String value1, String value2) {
            addCriterion("skillRequirement between", value1, value2, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andSkillrequirementNotBetween(String value1, String value2) {
            addCriterion("skillRequirement not between", value1, value2, "skillrequirement");
            return (Criteria) this;
        }

        public Criteria andPosttimeIsNull() {
            addCriterion("postTime is null");
            return (Criteria) this;
        }

        public Criteria andPosttimeIsNotNull() {
            addCriterion("postTime is not null");
            return (Criteria) this;
        }

        public Criteria andPosttimeEqualTo(Date value) {
            addCriterion("postTime =", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeNotEqualTo(Date value) {
            addCriterion("postTime <>", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeGreaterThan(Date value) {
            addCriterion("postTime >", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeGreaterThanOrEqualTo(Date value) {
            addCriterion("postTime >=", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeLessThan(Date value) {
            addCriterion("postTime <", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeLessThanOrEqualTo(Date value) {
            addCriterion("postTime <=", value, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeIn(List<Date> values) {
            addCriterion("postTime in", values, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeNotIn(List<Date> values) {
            addCriterion("postTime not in", values, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeBetween(Date value1, Date value2) {
            addCriterion("postTime between", value1, value2, "posttime");
            return (Criteria) this;
        }

        public Criteria andPosttimeNotBetween(Date value1, Date value2) {
            addCriterion("postTime not between", value1, value2, "posttime");
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

        public Criteria andUseridIsNull() {
            addCriterion("userId is null");
            return (Criteria) this;
        }

        public Criteria andUseridIsNotNull() {
            addCriterion("userId is not null");
            return (Criteria) this;
        }

        public Criteria andUseridEqualTo(String value) {
            addCriterion("userId =", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotEqualTo(String value) {
            addCriterion("userId <>", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThan(String value) {
            addCriterion("userId >", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridGreaterThanOrEqualTo(String value) {
            addCriterion("userId >=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThan(String value) {
            addCriterion("userId <", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLessThanOrEqualTo(String value) {
            addCriterion("userId <=", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridLike(String value) {
            addCriterion("userId like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotLike(String value) {
            addCriterion("userId not like", value, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridIn(List<String> values) {
            addCriterion("userId in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotIn(List<String> values) {
            addCriterion("userId not in", values, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridBetween(String value1, String value2) {
            addCriterion("userId between", value1, value2, "userid");
            return (Criteria) this;
        }

        public Criteria andUseridNotBetween(String value1, String value2) {
            addCriterion("userId not between", value1, value2, "userid");
            return (Criteria) this;
        }
    }

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