package org.zerock.partThree.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.partThree.dto.BoardDTO;
import org.zerock.partThree.dto.PageRequestDTO;
import org.zerock.partThree.service.BoardService;

@Controller
@RequestMapping("/board/")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public void list(@ModelAttribute("requestDTO")PageRequestDTO pageRequestDTO, Model model){
        model.addAttribute("result",boardService.getList(pageRequestDTO));
    }
    @GetMapping("/register")
    public void register(){

    }
    @PostMapping("/register")
    public String register(@ModelAttribute("dto")BoardDTO dto,
                           RedirectAttributes redirectAttributes
                           ){
        Long bno = boardService.register(dto);

        redirectAttributes.addFlashAttribute("msg",bno);
        return "redirect:/board/list";
    }
    @GetMapping({"/read","/modify"})
    public void read(@ModelAttribute("requestDTO")PageRequestDTO requestDTO,
                     Model model,
                     Long bno){

        BoardDTO boardDTO = boardService.get(bno);
        model.addAttribute("dto",boardDTO);
    }
    @PostMapping("/modify")
    public String modify(@ModelAttribute("dto")BoardDTO dto,
                         @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes
                         ){
        boardService.modify(dto);

        redirectAttributes.addAttribute("page",pageRequestDTO.getPage());
        redirectAttributes.addAttribute("type",pageRequestDTO.getType());
        redirectAttributes.addAttribute("keyword",pageRequestDTO.getKeyword());
        redirectAttributes.addAttribute("bno",dto.getBno());

        return "redirect:/board/read";

    }

    @PostMapping("/remove")
    public String remove(Long bno,RedirectAttributes redirectAttributes){

        boardService.removeWithReply(bno);
        redirectAttributes.addFlashAttribute("delMsg",bno);

        return "redirect:/board/list";
    }



}
