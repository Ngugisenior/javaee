package com.formula.racing.Formula.One.Racing.controller;

import com.formula.racing.Formula.One.Racing.domain.Progress;
import com.formula.racing.Formula.One.Racing.services.ProgressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/progress")
public class ProgressController {
    private final ProgressService progressService;

    @Autowired
    public ProgressController(ProgressService progressService) {
        this.progressService = progressService;
    }

    @RequestMapping(value = "/create_task/{task_id}", method = RequestMethod.GET)
    String create_task(@PathVariable("task_id") String task_id, Model model){
        Progress t = progressService.findByID(Long.parseLong(task_id)).get();
        if(t.isCreateStatus()){
            t.setCreateStatus(false);
        }
        else {
            t.setCreateStatus(true);
        }
        progressService.save(t);
        return "redirect:/";
    }

    @RequestMapping(value = "/read_task/{task_id}", method = RequestMethod.GET)
    String read_task(@PathVariable("task_id") String task_id, Model model){
        Progress t = progressService.findByID(Long.parseLong(task_id)).get();
        if(t.isReadStatus()){
            t.setReadStatus(false);
        }
        else {
            t.setReadStatus(true);
        }
        progressService.save(t);
        return "redirect:/";
    }

    @RequestMapping(value = "/update_task/{task_id}", method = RequestMethod.GET)
    String update_task(@PathVariable("task_id") String task_id, Model model){
        Progress t = progressService.findByID(Long.parseLong(task_id)).get();
        if(t.isUpdateStatus()){
            t.setUpdateStatus(false);
        }
        else {
            t.setUpdateStatus(true);
        }
        progressService.save(t);
        return "redirect:/";
    }

    @RequestMapping(value = "/delete_task/{task_id}", method = RequestMethod.GET)
    String delete_task(@PathVariable("task_id") String task_id, Model model){
        Progress t = progressService.findByID(Long.parseLong(task_id)).get();
        if(t.isDeleteStatus()){
            t.setDeleteStatus(false);
        }
        else {
            t.setDeleteStatus(true);
        }
        progressService.save(t);
        return "redirect:/";
    }
}
