package kr.pethub.webapp.admin.board.controller;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import kr.pethub.core.authority.Auth;
import kr.pethub.core.utils.SysUtil;
import kr.pethub.webapp.admin.board.model.Board;
import kr.pethub.webapp.admin.board.service.BoardService;
import kr.pethub.webapp.admin.board.service.FileService;


@Controller
@RequestMapping("/adm")
public class BoardController{
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private BoardService boardService;

	@Autowired
	private FileService fileService;
	
	/**
	 * 게시판 목록
	 * @param board
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/{flag}/list")
	public String boardList(HttpServletRequest request, @ModelAttribute Board board, @PathVariable String flag,  Model model) {
		
		List<Board> list = boardService.selectBoardList(board);
		
		model.addAttribute("list", list);
		
		 return "admin:site/board/boardList";
	} 
	
	/**
	 * 게시판 조회
	 * @param board
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/{flag}/view")
	public String boardView(@ModelAttribute Board board, @PathVariable String flag, Model model) {
		
		model.addAttribute("boardInfo", boardService.selectBoard(board.getBoardSrl()));
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("fileTp", "board");
		map.put("fileRefSrl", board.getBoardSrl());
		model.addAttribute("fileList", fileService.selectFileList(map));
		
		return "admin:site/board/boardView";
	} 
	
	/**
	 * 게시판 폼
	 * @param board
	 * @param flag
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/board/{flag}/{action}/form")
	public String inserBoardForm(
			@ModelAttribute Board board, 
			@PathVariable String flag, 
			@PathVariable String action, 
			Model model) {
		
		//수정 폼
		if("update".equals(action)){
			model.addAttribute("boardInfo", boardService.selectBoard(board.getBoardSrl()));
			
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("fileTp", "board");
			map.put("fileRefSrl", board.getBoardSrl());
			model.addAttribute("fileList", fileService.selectFileList(map));
		//댓글 폼
		}else if("reply".equals(action)){
			Board boardInfo = boardService.selectBoard(board.getBoardSrl());
			boardInfo.setTitle("RE : " + boardInfo.getTitle());
			boardInfo.setContent("");
			boardInfo.setFileCnt(0);
			model.addAttribute("boardInfo", boardInfo);
		}
		
		return "admin:site/board/boardForm";
	} 

	/**
	 * 게시판 등록
	 * @param request
	 * @param board
	 * @param flag
	 * @param auth
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/board/{flag}/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object>  insertBoard(HttpServletRequest request, Board board, @PathVariable String flag, Auth auth) throws JsonParseException, JsonMappingException, IOException {
		
		board.setIp(SysUtil.getRemoteIp(request));	
		board.setUserId(auth.getUserId());
		boardService.insertBoard(board);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardSrl", board.getBoardSrl());	
		
		return map;
		
	} 
	
	/**
	 * 게시판 댓글 등록
	 * @param request
	 * @param board
	 * @param flag
	 * @param auth
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/board/{flag}/reply", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object>  replyBoard(HttpServletRequest request, Board board, @PathVariable String flag, Auth auth) throws JsonParseException, JsonMappingException, IOException {
		
		board.setIp(SysUtil.getRemoteIp(request));	
		board.setUserId(auth.getUserId());
		boardService.replyBoard(board);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardSrl", board.getBoardSrl());	
		
		return map;
		
	} 
	
	/**
	 * 게시판 수정
	 * @param request
	 * @param board
	 * @param flag
	 * @param auth
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@ResponseBody
	@RequestMapping(value="/board/{flag}/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public Map<String,Object> updateBoard(HttpServletRequest request, Board board, @PathVariable String flag, Auth auth) throws JsonParseException, JsonMappingException, IOException {
		
		board.setIp(SysUtil.getRemoteIp(request));
		board.setUserId(auth.getUserId());
		boardService.updateBoard(board);
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("boardSrl", board.getBoardSrl());	
		
		return map;
		
	} 
	
	/**
	 * 게시판 삭제
	 * @param board
	 */
	@ResponseBody
	@RequestMapping(value="/board/{flag}/delete", method = RequestMethod.POST)
	public void deleteBoard(Board board, Auth auth) {
		board.setUserId(auth.getUserId());
		boardService.deleteBoard(board);
	} 
	

}