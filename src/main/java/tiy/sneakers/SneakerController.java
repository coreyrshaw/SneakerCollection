package tiy.sneakers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class SneakerController {
    @Autowired
   SneakerRepository sneakers;
    
    @RequestMapping(path = "/add-sneaker", method = RequestMethod.POST)
    public String addSneaker(HttpSession session,String jordanModel,int jordanModelNumber, String jordanColor, int jordanReleaseYear, double jordanPrice,String jordanPhotoLink, boolean coplist) {
        User user =(User) session.getAttribute("user");
        Sneaker sneaker = new Sneaker(jordanModel,jordanModelNumber, jordanColor, jordanReleaseYear,jordanPrice,jordanPhotoLink, coplist,user);
        sneakers.save(sneaker);
        return "redirect:/";
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model, HttpSession session) {
        if (session.getAttribute("user") != null) {
            model.addAttribute("user", session.getAttribute("user"));
        }
        User savedUser = (User)session.getAttribute("user");
        List<Sneaker> sneakerList = new ArrayList<Sneaker>();
        if (savedUser != null) {
            sneakerList = sneakers.findByUser(savedUser);
        }
//        } else {
//            Iterable<Sneaker> allSneakers = sneakers.findAll();
//            for (Sneaker sneaker : allSneakers) {
//                sneakerList.add(sneaker);
//            }
//        }
        model.addAttribute("sneakers", sneakerList);
        return "home";
    }

    @RequestMapping(path = "/delete", method = RequestMethod.GET)
    public String deleteSneaker(Model model, Integer sneakerID) {
        if (sneakerID!= null) {
            sneakers.delete(sneakerID);
        }

        return "redirect:/";
    }

    @RequestMapping(path = "/toggle", method = RequestMethod.GET)
    public String toggleSneaker(Model model, Integer sneakerID) {
        if (sneakerID != null) {
            Sneaker kicks = sneakers.findOne(sneakerID); //calls inside Repository, finding the id.
            kicks.coplist = !kicks.coplist;
            sneakers.save(kicks);

        }

        return "redirect:/";
    }

    @Autowired
    UserRepository users;

    @RequestMapping(path = "/login", method = RequestMethod.POST)
    public String login(HttpSession session, String userName, String password) throws Exception {
        User user = users.findByName(userName);
        if (user == null) {
            user = new User(userName, password);
            users.save(user);
        } else if (!password.equals(user.getPassword())) {
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

