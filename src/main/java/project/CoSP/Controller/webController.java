package project.CoSP.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import project.CoSP.Exception.generalErrorMessageException;
import project.CoSP.Model.Code;
import project.CoSP.codeService;
import java.util.List;
import java.util.UUID;

@Controller
public class webController {
    @Autowired
    private codeService service;

    private static final Logger logger = LoggerFactory.getLogger(webController.class);

    @GetMapping(value = "/code/{id}", produces = "text/html")
    public ModelAndView getCode(@PathVariable UUID id){
        logger.info("Get at /code/{id}");

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("code.html");
        Code code = service.getCode(id);

        if(code==null){
            throw new generalErrorMessageException("");
        }

        modelAndView.addObject("date", code.getTime());

        if(code.getRestrictionType()==2){

            modelAndView.addObject("ifHasTimeRestriction", true);
            modelAndView.addObject("time", code.getTimeRestriction());

        }else if(code.getRestrictionType()==3){

            modelAndView.addObject("ifHasViewRestriction", true);
            modelAndView.addObject("view", code.getViewRestriction());

        }else if(code.getRestrictionType()==4){

            modelAndView.addObject("ifHasViewRestriction", true);
            modelAndView.addObject("view", code.getViewRestriction());
            modelAndView.addObject("ifHasTimeRestriction", true);
            modelAndView.addObject("time", code.getTimeRestriction());

        }

        modelAndView.addObject("code", code.getCode());
        return modelAndView;
    }

    @GetMapping(value = "/code/new", produces = "text/html")
    public RedirectView sumbitNewCode(){
        logger.info("GET at /code/new");
        return new RedirectView("/create.html");
    }

    @GetMapping(value = "code/latest", produces = "text/html")
    public ModelAndView getLatestCode(ModelAndView modelAndView){
        logger.info("GET at /code/latest");
        modelAndView.setViewName("latest.html");
        List<Code> get10Code = service.getLatestCode();
        modelAndView.addObject("listCodes", get10Code);
        return modelAndView;
    }
}
