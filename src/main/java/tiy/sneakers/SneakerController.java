package tiy.sneakers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SneakerController {
    @Autowired
   SneakerRepository sneakers;
    
    @RequestMapping(path = "/add-sneaker", method = RequestMethod.POST)
    public String addSneaker(String jordanModel,int jordanModelNumber, String jordanColor, int jordanReleaseYear, double jordanPrice,String jordanPhotoLink) {
        Sneaker sneaker = new Sneaker(jordanModel,jordanModelNumber, jordanColor, jordanReleaseYear,jordanPrice,jordanPhotoLink);
        sneakers.save(sneaker);
        return "redirect:/";
    }


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(Model model) {
        Iterable<Sneaker> allSneakers = sneakers.findAll();
        List<Sneaker>sneakerList = new ArrayList<Sneaker>();
        for (Sneaker sneaker : allSneakers) {
            sneakerList.add(sneaker);
        }
        model.addAttribute("sneakers", sneakerList);
        return "home";
    }




}

