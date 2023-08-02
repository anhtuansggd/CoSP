package project.CoSP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.CoSP.Exception.generalErrorMessageException;
import project.CoSP.Model.Code;
import java.util.*;

@Service
public class codeService {
    @Autowired
    private codeRepository codeRepo;

    public void firstSave(Code code){
        code.setRestriction();
        codeRepo.save(code);
    }

    public void saveCode(Code code){
        codeRepo.save(code);
    }

    /***
     *saveCode in getCode save the state of requiredCode
     ***/

    public Code getCode(UUID id){
        Optional<Code> optionalCode = Optional.ofNullable(codeRepo.findById(id).orElse(null));
        if(optionalCode.isPresent()){
            Code requiredCode = optionalCode.get();

            if(requiredCode.getLastAccessedTime()==null){
                requiredCode.setLastAccessedTime(requiredCode.getTime());
            }

            if(requiredCode.getRestrictionType()==2 && requiredCode.getTimeRestriction()<0){
                codeRepo.delete(requiredCode);
                return null;
            }

            if(requiredCode.getRestrictionType()==3 && requiredCode.getViewRestriction()<0){
                codeRepo.delete(requiredCode);
                return null;
            }

            if(requiredCode.getRestrictionType()==4 && (requiredCode.getTimeRestriction()<0 && requiredCode.getViewRestriction()<0)){
                codeRepo.delete(requiredCode);
                return null;
            }

            if(requiredCode.getRestrictionType()==1){
                return requiredCode;
            }

            if(requiredCode.getRestrictionType()==2){
                requiredCode.decreTimeRestriction();
                saveCode(requiredCode);
                return requiredCode;
            }

            if(requiredCode.getRestrictionType()==3){
                requiredCode.decreViewRestriction();
                saveCode(requiredCode);
                return requiredCode;
            }

            if(requiredCode.getRestrictionType()==4){
                requiredCode.decreViewRestriction();
                requiredCode.decreTimeRestriction();
                saveCode(requiredCode);
                return requiredCode;
            }
            return null;

        }else throw new generalErrorMessageException("");
    }

    public Code getLast(){
        return codeRepo.findTop1ByOrderByTime();
    }

    public List<Code> getAll(){
        return codeRepo.findAll();
    }

    public long getCount() {
        return codeRepo.count();
    }

    public void deleteCode(Code code) {
        codeRepo.delete(code);
    }

    public void deleteCodeById(UUID id) {
        codeRepo.deleteById(id);
    }

    public List<Code> getLatestCode(){
        List<Code> result = codeRepo.findTop10ByRestrictionTypeOrderByTimeDesc(1);
        return result;
    }

}
