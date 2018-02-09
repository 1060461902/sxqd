package edu.zjgsu.ito.model;

import java.util.ArrayList;
import java.util.List;

public class TableDataExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public TableDataExample() {
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

        public Criteria andTimesIsNull() {
            addCriterion("times is null");
            return (Criteria) this;
        }

        public Criteria andTimesIsNotNull() {
            addCriterion("times is not null");
            return (Criteria) this;
        }

        public Criteria andTimesEqualTo(String value) {
            addCriterion("times =", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotEqualTo(String value) {
            addCriterion("times <>", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThan(String value) {
            addCriterion("times >", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesGreaterThanOrEqualTo(String value) {
            addCriterion("times >=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThan(String value) {
            addCriterion("times <", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLessThanOrEqualTo(String value) {
            addCriterion("times <=", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesLike(String value) {
            addCriterion("times like", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotLike(String value) {
            addCriterion("times not like", value, "times");
            return (Criteria) this;
        }

        public Criteria andTimesIn(List<String> values) {
            addCriterion("times in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotIn(List<String> values) {
            addCriterion("times not in", values, "times");
            return (Criteria) this;
        }

        public Criteria andTimesBetween(String value1, String value2) {
            addCriterion("times between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andTimesNotBetween(String value1, String value2) {
            addCriterion("times not between", value1, value2, "times");
            return (Criteria) this;
        }

        public Criteria andCompanyNumIsNull() {
            addCriterion("company_num is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNumIsNotNull() {
            addCriterion("company_num is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNumEqualTo(Long value) {
            addCriterion("company_num =", value, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumNotEqualTo(Long value) {
            addCriterion("company_num <>", value, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumGreaterThan(Long value) {
            addCriterion("company_num >", value, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumGreaterThanOrEqualTo(Long value) {
            addCriterion("company_num >=", value, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumLessThan(Long value) {
            addCriterion("company_num <", value, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumLessThanOrEqualTo(Long value) {
            addCriterion("company_num <=", value, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumIn(List<Long> values) {
            addCriterion("company_num in", values, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumNotIn(List<Long> values) {
            addCriterion("company_num not in", values, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumBetween(Long value1, Long value2) {
            addCriterion("company_num between", value1, value2, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCompanyNumNotBetween(Long value1, Long value2) {
            addCriterion("company_num not between", value1, value2, "companyNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumIsNull() {
            addCriterion("current_recruit_num is null");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumIsNotNull() {
            addCriterion("current_recruit_num is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumEqualTo(Long value) {
            addCriterion("current_recruit_num =", value, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumNotEqualTo(Long value) {
            addCriterion("current_recruit_num <>", value, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumGreaterThan(Long value) {
            addCriterion("current_recruit_num >", value, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumGreaterThanOrEqualTo(Long value) {
            addCriterion("current_recruit_num >=", value, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumLessThan(Long value) {
            addCriterion("current_recruit_num <", value, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumLessThanOrEqualTo(Long value) {
            addCriterion("current_recruit_num <=", value, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumIn(List<Long> values) {
            addCriterion("current_recruit_num in", values, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumNotIn(List<Long> values) {
            addCriterion("current_recruit_num not in", values, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumBetween(Long value1, Long value2) {
            addCriterion("current_recruit_num between", value1, value2, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentRecruitNumNotBetween(Long value1, Long value2) {
            addCriterion("current_recruit_num not between", value1, value2, "currentRecruitNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumIsNull() {
            addCriterion("current_student_num is null");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumIsNotNull() {
            addCriterion("current_student_num is not null");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumEqualTo(Long value) {
            addCriterion("current_student_num =", value, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumNotEqualTo(Long value) {
            addCriterion("current_student_num <>", value, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumGreaterThan(Long value) {
            addCriterion("current_student_num >", value, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumGreaterThanOrEqualTo(Long value) {
            addCriterion("current_student_num >=", value, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumLessThan(Long value) {
            addCriterion("current_student_num <", value, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumLessThanOrEqualTo(Long value) {
            addCriterion("current_student_num <=", value, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumIn(List<Long> values) {
            addCriterion("current_student_num in", values, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumNotIn(List<Long> values) {
            addCriterion("current_student_num not in", values, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumBetween(Long value1, Long value2) {
            addCriterion("current_student_num between", value1, value2, "currentStudentNum");
            return (Criteria) this;
        }

        public Criteria andCurrentStudentNumNotBetween(Long value1, Long value2) {
            addCriterion("current_student_num not between", value1, value2, "currentStudentNum");
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