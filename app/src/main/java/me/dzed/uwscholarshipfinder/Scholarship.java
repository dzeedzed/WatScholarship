package me.dzed.uwscholarshipfinder;

import java.util.List;

/**
 * Created by dzklavier on 2016-01-20.
 */
public class Scholarship {

    // Scholarship information types given by UW API
    private String id;
    private String title;
    private String status;
    private String value;
    private String type;
    private String enrollmentYear;
    private String eligibility;
    private String instructions;
    private String additional;
    private String description;
    private String citizenship;
    private String programs;
    private String deadlines;
    private String contact;
    private String link;
    private boolean math;
    private boolean engineering;
    private boolean arts;
    private boolean ahs;
    private boolean science;
    private boolean environment;


    public Scholarship(String id, String title, String status, String value,
                       String type, String enrollmentYear, String eligibility,
                       String instructions, String additional, String description,
                       String citizenship, String programs, String deadlines,
                       String contact, String link, boolean math, boolean engineering,
                       boolean arts, boolean ahs, boolean science, boolean
                       environment) {
        this.id = id;
        this.title = title;
        this.status = status;
        this.value = value;
        this.description = description;
        this.citizenship = citizenship;
        this.programs = programs;
        this.type = type;
        this.deadlines = deadlines;
        this.contact = contact;
        this.link = link;
        this.enrollmentYear = enrollmentYear;
        this.eligibility = eligibility;
        this.instructions = instructions;
        this.additional = additional;
        this.math = math;
        this.engineering = engineering;
        this.arts = arts;
        this.ahs = ahs;
        this.science = science;
        this.environment = environment;

    }

    // Setters and Getters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCitizenship() {
       return citizenship;
    }

    public void setCitizenship(String citizenship) {
        this.citizenship = citizenship;
    }

    public String getPrograms() {
        return programs;
    }

    public void setPrograms(String programs) {
        this.programs = programs;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(String enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getAdditional() {
        return additional;
    }

    public void setAdditional(String additional) {
        this.additional = additional;
    }

    public String getDeadlines() {
        return deadlines;
    }

    public void setDeadlines(String deadlines) {
        this.deadlines = deadlines;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public boolean isMath() {
        return math;
    }

    public void setMath(boolean math) {
        this.math = math;
    }

    public boolean isEngineering() {
        return engineering;
    }

    public void setEngineering(boolean engineering) {
        this.engineering = engineering;
    }

    public boolean isArts() {
        return arts;
    }

    public void setArts(boolean arts) {
        this.arts = arts;
    }

    public boolean isAhs() {
        return ahs;
    }

    public void setAhs(boolean ahs) {
        this.ahs = ahs;
    }

    public boolean isScience() {
        return science;
    }

    public void setScience(boolean science) {
        this.science = science;
    }

    public boolean isEnvironment() {
        return environment;
    }

    public void setEnvironment(boolean environment) {
        this.environment = environment;
    }

    @Override
    public String toString() {
        String result = title + "\n";
        return result;
    }

}
