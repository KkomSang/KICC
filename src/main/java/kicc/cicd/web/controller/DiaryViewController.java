package kicc.cicd.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DiaryViewController {
    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/

    /*V1*/
//    @GetMapping("/diary")
//    public String diaryList() {
//        return "diary-list-v1";
//    }
//
//    @GetMapping("/diary/{id}")
//    public String diaryDetail(@PathVariable Long id, Model model) {
//        model.addAttribute("id", id);
//        return "diary-detail-v1";
//    }
//
//    @GetMapping("/diary/write")
//    public String diaryWrite() {
//        return "diary-write-v1";
//    }


    /*~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
    /*V2*/
    @GetMapping("/diary")
    public String diaryList() {
        return "diary-list-v2";
    }

    @GetMapping("/diary/{id}")
    public String diaryDetail(@PathVariable Long id, Model model) {
        model.addAttribute("id", id);
        return "diary-detail-v2";
    }

    @GetMapping("/diary/write")
    public String diaryWrite() {
        return "diary-write-v2";
    }

}
