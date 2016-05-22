package tiy.sneakers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sulton on 5/22/2016.
 */


    @Controller
    public class SneakerController {
        @Autowired
        SneakerRepository sneakers;

        @RequestMapping(path = "/add-sneaker", method = RequestMethod.POST)

        public String addSneaker(HttpSession session, String jordanModel,int jordanModelNumber, String jordanColor, int jordanReleaseYear, double jordanPrice,String jordanPhotoLink) {
            User user = (User) session.getAttribute("user");
            Sneaker sneaker = new Sneaker(jordanModel,jordanModelNumber, jordanColor, jordanReleaseYear,jordanPrice,jordanPhotoLink, user);
            sneakers.save(sneaker);
            return "redirect:/";
        }


        @RequestMapping(path = "/", method = RequestMethod.GET)
        public String home(Model model, HttpSession session, String newModel, Integer modelNo, String color, Integer releaseYear, Double price) {
            if (session.getAttribute("user") != null) {
                model.addAttribute("user", session.getAttribute("user"));}
            List<Sneaker> sneakerList = new ArrayList<Sneaker>();
            if (newModel != null) {
                sneakerList = sneakers.findByModel(newModel);
            } else if (modelNo != null){
                sneakerList = sneakers.findByModelNo(modelNo);
            } else if (color != null){
                sneakerList = sneakers.findByColor(color);
            } else if( releaseYear != null){
                sneakerList = sneakers.findByReleaseYear(releaseYear);
            } else if(price != null){
                sneakerList = sneakers.findByPrice(price);
            } else{
                Iterable<Sneaker> allSneakers = sneakers.findAll();
                for (Sneaker sneaker : allSneakers) {
                    sneakerList.add(sneaker);
            }

            }
            model.addAttribute("sneakers", sneakerList);
            return "home";
        }

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {

        User user = users.findFirstByName(userName);
        if (user == null) {
            user = new User(userName, password);
            users.save(user);
        }
        else if (!password.equals(user.password)) {
            throw new Exception("Incorrect password");
        }

        session.setAttribute("user", user);
        return "redirect:/";
    }

    @RequestMapping(path = "/logout", method = RequestMethod.POST)
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }



    }
