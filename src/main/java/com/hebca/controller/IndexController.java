package com.hebca.controller;

import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hebca.model.JsonResp;
import com.hebca.model.PropertyInfo;
import com.hebca.model.UserModel;
import com.hebca.service.UserService;
import org.bouncycastle.util.encoders.Hex;
import org.jasig.cas.client.util.AssertionHolder;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;
    @RequestMapping("/index")
    public String index(){
        return "index/index";
    }

    @RequestMapping("/admin")
    public String world(HttpServletRequest request, Model model){
        Assertion assertion=AssertionHolder.getAssertion();
        Map<String,Object> map=assertion.getPrincipal().getAttributes();
        Map<String,Object> transMap=map.entrySet().stream()
                .map(x->{
                    try {
                        x.setValue(URLDecoder.decode((String)x.getValue(),"UTF-8"));
                    }
                    catch (Exception ex){
                    }
                    return x;
                })
                .collect(toMap(
                        (x)->{

                            return x.getKey();
                        },
                        (x)->{
                            try{
                                return URLDecoder.decode((String)x.getValue(),"UTF-8");
                            }
                            catch (Exception ex) {
                                return x.getValue();
                            }

                        }
                ));

        String name=transMap.getOrDefault("user_name","").toString();
        String identity=transMap.getOrDefault("user_identity","").toString();
        String rolename=transMap.getOrDefault("userRoleName","").toString();
        UserModel userModel=userService.findByIdentity(identity);
        String content="";
        String hash="";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS,true);
        objectMapper.configure(MapperFeature.SORT_PROPERTIES_ALPHABETICALLY,true);
        try{
            content = objectMapper.writeValueAsString(transMap);
            hash=md5(content.getBytes("UTF-8"));
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        if(request.getSession().getAttribute("login")==null) {
            if (userModel == null) {
                userModel = new UserModel(name, identity, rolename, 1, content,hash);
                userService.save(userModel);

            } else {
                if(!userModel.getUserhash().equalsIgnoreCase(hash)){
                    userModel.setContent(content);
                    userModel.setUserhash(hash);
                }
                userModel.setLoginCount(userModel.getLoginCount() + 1);
                userService.save(userModel);
            }
        }
        request.getSession().setAttribute("login","login");
        model.addAttribute("userModel",userModel);
        model.addAttribute("attribute",transMap);
        model.addAttribute("name",assertion.getPrincipal().getName());
        return "index/world";
    }
    @RequestMapping("/detail")
    public String detail(HttpServletRequest request, Model model){
        return "index/detail";
    }
    @ResponseBody
    @RequestMapping("/detailjson")
    public JsonResp detailJson(HttpServletRequest request, Model model){
        Assertion assertion=AssertionHolder.getAssertion();
        Map<String,Object> map=assertion.getPrincipal().getAttributes();
        Map<String,Object> transMap=map.entrySet().stream()
                .map(x->{
                    try {
                        x.setValue(URLDecoder.decode((String)x.getValue(),"UTF-8"));
                    }
                    catch (Exception ex){
                    }
                    return x;
                })
                .collect(toMap(Map.Entry::getKey,
                        (x)->{
                            try{
                                return URLDecoder.decode((String)x.getValue(),"UTF-8");
                            }
                            catch (Exception ex) {
                                return x.getValue();
                            }
                        }
                ));
        List<PropertyInfo> propertyInfos=transMap.entrySet().stream()
                .map(x->new PropertyInfo(x.getKey(),x.getValue().toString()))
                .collect(toList());

        return new JsonResp<PropertyInfo>(1L,propertyInfos);
    }
    private String md5(byte[] b) throws Exception{
        MessageDigest md5=MessageDigest.getInstance("MD5");
        return new String(Hex.encode(md5.digest(b)));
    }

}
