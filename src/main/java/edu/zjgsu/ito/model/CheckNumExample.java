package edu.zjgsu.ito.model;

import java.util.ArrayList;
import java.util.List;

public class CheckNumExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Integer limit;

    private Integer offset;

    public CheckNumExample() {
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

        public Criteria andClockinnumIsNull() {
            addCriterion("clockInNum is null");
            return (Criteria) this;
        }

        public Criteria andClockinnumIsNotNull() {
            addCriterion("clockInNum is not null");
            return (Criteria) this;
        }

        public Criteria andClockinnumEqualTo(Integer value) {
            addCriterion("clockInNum =", value, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumNotEqualTo(Integer value) {
            addCriterion("clockInNum <>", value, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumGreaterThan(Integer value) {
            addCriterion("clockInNum >", value, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("clockInNum >=", value, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumLessThan(Integer value) {
            addCriterion("clockInNum <", value, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumLessThanOrEqualTo(Integer value) {
            addCriterion("clockInNum <=", value, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumIn(List<Integer> values) {
            addCriterion("clockInNum in", values, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumNotIn(List<Integer> values) {
            addCriterion("clockInNum not in", values, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumBetween(Integer value1, Integer value2) {
            addCriterion("clockInNum between", value1, value2, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockinnumNotBetween(Integer value1, Integer value2) {
            addCriterion("clockInNum not between", value1, value2, "clockinnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumIsNull() {
            addCriterion("clockOutNum is null");
            return (Criteria) this;
        }

        public Criteria andClockoutnumIsNotNull() {
            addCriterion("clockOutNum is not null");
            return (Criteria) this;
        }

        public Criteria andClockoutnumEqualTo(Integer value) {
            addCriterion("clockOutNum =", value, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumNotEqualTo(Integer value) {
            addCriterion("clockOutNum <>", value, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumGreaterThan(Integer value) {
            addCriterion("clockOutNum >", value, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("clockOutNum >=", value, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumLessThan(Integer value) {
            addCriterion("clockOutNum <", value, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumLessThanOrEqualTo(Integer value) {
            addCriterion("clockOutNum <=", value, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumIn(List<Integer> values) {
            addCriterion("clockOutNum in", values, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumNotIn(List<Integer> values) {
            addCriterion("clockOutNum not in", values, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumBetween(Integer value1, Integer value2) {
            addCriterion("clockOutNum between", value1, value2, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andClockoutnumNotBetween(Integer value1, Integer value2) {
            addCriterion("clockOutNum not between", value1, value2, "clockoutnum");
            return (Criteria) this;
        }

        public Criteria andDayIsNull() {
            addCriterion("day is null");
            return (Criteria) this;
        }

        public Criteria andDayIsNotNull() {
            addCriterion("day is not null");
            return (Criteria) this;
        }

        public Criteria andDayEqualTo(String value) {
            addCriterion("day =", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotEqualTo(String value) {
            addCriterion("day <>", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThan(String value) {
            addCriterion("day >", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayGreaterThanOrEqualTo(String value) {
            addCriterion("day >=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThan(String value) {
            addCriterion("day <", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLessThanOrEqualTo(String value) {
            addCriterion("day <=", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayLike(String value) {
            addCriterion("day like", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotLike(String value) {
            addCriterion("day not like", value, "day");
            return (Criteria) this;
        }

        public Criteria andDayIn(List<String> values) {
            addCriterion("day in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotIn(List<String> values) {
            addCriterion("day not in", values, "day");
            return (Criteria) this;
        }

        public Criteria andDayBetween(String value1, String value2) {
            addCriterion("day between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andDayNotBetween(String value1, String value2) {
            addCriterion("day not between", value1, value2, "day");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumIsNull() {
            addCriterion("lastDayAttendNum is null");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumIsNotNull() {
            addCriterion("lastDayAttendNum is not null");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumEqualTo(Integer value) {
            addCriterion("lastDayAttendNum =", value, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumNotEqualTo(Integer value) {
            addCriterion("lastDayAttendNum <>", value, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumGreaterThan(Integer value) {
            addCriterion("lastDayAttendNum >", value, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("lastDayAttendNum >=", value, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumLessThan(Integer value) {
            addCriterion("lastDayAttendNum <", value, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumLessThanOrEqualTo(Integer value) {
            addCriterion("lastDayAttendNum <=", value, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumIn(List<Integer> values) {
            addCriterion("lastDayAttendNum in", values, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumNotIn(List<Integer> values) {
            addCriterion("lastDayAttendNum not in", values, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumBetween(Integer value1, Integer value2) {
            addCriterion("lastDayAttendNum between", value1, value2, "lastdayattendnum");
            return (Criteria) this;
        }

        public Criteria andLastdayattendnumNotBetween(Integer value1, Integer value2) {
            addCriterion("lastDayAttendNum not between", value1, value2, "lastdayattendnum");
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