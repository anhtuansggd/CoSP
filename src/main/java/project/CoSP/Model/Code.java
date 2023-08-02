package project.CoSP.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;
import project.CoSP.Exception.generalErrorMessageException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
public class Code {
    private String code;
    private String time;
    private String lastAccessedTime;
    private long timeRestriction;
    private int viewRestriction;

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @Transient
    private boolean hasTimeRestriction;

    @Transient
    private boolean hasViewRestriction;

    /***
     * 1: NO RESTRICTION TIME VIEWS
     * 2: TIME RESTRICTED
     * 3: VIEWS RESTRICTED
     * 4: BOTH TIME & VIEWS RESTRICTED
     ***/
    private int restrictionType;

    public Code() {}

    public Code(String code) {
        this.code = code;
    }

    public Code(String code, LocalDateTime time, int timeRestriction, int viewRestriction) {
        this.code = code;
        this.time = getTimeConverted(time);
        this.timeRestriction = timeRestriction;
        this.viewRestriction = viewRestriction;
        this.lastAccessedTime = this.time;
        setRestriction();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public UUID getId() {
        return id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = getTimeConverted(time);
    }

    public String getTimeConverted(LocalDateTime time){
        System.out.println();
        String dateFormatter = "dd-MM-yyyy HH:mm:ss.SSSS";
        return time.format(DateTimeFormatter.ofPattern(dateFormatter));
    }

    @JsonProperty("time")
    public long getTimeRestriction() {
        return timeRestriction;
    }

    public void setTimeRestriction(int timeRestriction) {
        this.timeRestriction = timeRestriction;
    }

    public void decreTimeRestriction(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSSS");
        LocalDateTime lastAccessedTime = LocalDateTime.parse(getLastAccessedTime(), formatter);
        LocalDateTime timeNow = LocalDateTime.now();

        long secondsDiff = Duration.between(lastAccessedTime, timeNow).getSeconds();
        this.timeRestriction -= secondsDiff;

        if(this.timeRestriction<0){
            throw new generalErrorMessageException("");
        }
        setLastAccessedTime(getTimeConverted(timeNow));
    }

    @JsonProperty("views")
    public int getViewRestriction() {
        return viewRestriction;
    }

    public void setViewRestriction(int viewRestriction) {
        this.viewRestriction = viewRestriction;
    }

    public void decreViewRestriction() {
        this.viewRestriction -=1;
        if(this.viewRestriction<0){
            throw new generalErrorMessageException("");
        }
    }

    public boolean ifHasTimeRestriction() {
        return this.hasTimeRestriction;
    }

    public boolean ifHasViewRestriction(){ return this.hasViewRestriction; }

    public int getRestrictionType(){ return this.restrictionType; }

    public void setRestriction(){
        //NO RESTRICTION TIME VIEWS
        if(this.viewRestriction<=0 && this.timeRestriction<=0){
            this.restrictionType = 1;
        }
        //TIME RESTRICTED
        if(this.viewRestriction<=0 && this.timeRestriction>1){
            this.restrictionType = 2;
        }
        //VIEWS RESTRICTED
        if(this.viewRestriction>1 && this.timeRestriction<=0){
            this.restrictionType = 3;
        }
        //BOTH TIME & VIEWS RESTRICTED
        if(this.viewRestriction>1 && this.timeRestriction>1){
            this.restrictionType = 4;
        }
    }

    public void setLastAccessedTime(String time){
        this.lastAccessedTime = time;
    }

    public String getLastAccessedTime(){
        return this.lastAccessedTime;
    }
}

