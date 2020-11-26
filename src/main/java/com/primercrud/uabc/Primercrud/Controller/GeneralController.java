package com.primercrud.uabc.Primercrud.Controller;

import com.primercrud.uabc.Primercrud.Entity.Alumno;
import com.primercrud.uabc.Primercrud.Entity.Intendente;
import com.primercrud.uabc.Primercrud.Entity.Persona;
import com.primercrud.uabc.Primercrud.Entity.Profesor;
import com.primercrud.uabc.Primercrud.Service.Implementation.PersonaServiceImplementation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/university")
public class GeneralController {

    private Persona persona;

    @Autowired
    PersonaServiceImplementation service;

    @GetMapping(value = "/index")
    public String showPersonal(Model model) {
        model.addAttribute("personas", service.getAllPersonas());
        return "university/index";
    }

/*    @PostMapping(value = "/addProfesor")
    public String addProfesor(@ModelAttribute Object object, RedirectAttributes redirectAttributes) {
        Persona newPersona = (Persona) object;
        switch (newPersona.getType()){
            case "Alumno":
                service.savePersona((Alumno) newPersona);
                break;
            case "Profesor":
                service.savePersona((Profesor) newPersona);
                break;
            case "Intendente":
                service.savePersona((Intendente) newPersona);
                break;
            default:
                break;
        }
        redirectAttributes.addFlashAttribute("Mensaje", "¡Agregado correctamente!");
        return "redirect:/university/index";
    }*/

    @GetMapping(value = "/delete/{matricula}")
    public String deletePersona(@PathVariable("matricula") Integer matricula, Model model) {
        model.addAttribute("personas", service.deletePersona(matricula));
        if (service.deletePersona(matricula)) {
            return "redirect:/university/index";
        }
        return "redirect:/university/index";
    }

    @GetMapping(value = "/edit/{matricula}")
    public String editPersona(@PathVariable("matricula") Integer matricula, Model model) {
        if (service.existPersona(matricula)) {
            //En las condiciones de abajo verifico si el objeto guardado es una instancia de alguno
            //de las tres entidades para redireccionarlos a sus respectivos paginas de editar.
            //Lo hice así más que nada por que quería aprovechar un solo método, y bueno... no solo métodos.
            //Tambien trate de hacer una pagina para t0do (un controller, un service, un personaService, etc.) Pero se me complico
            //usando la clase abstracta. No supe como castear e identificar de que instancia es la clase que recibe el metodo antes de recibirlo con el @ModelAttribute... :'c
            //Es decir, no me dejó poner @ModelAttribute Persona persona, por que es abstracto. Mi idea era poder recibirlo de esa manera y luego castearlo para guardarlo,
            //pero no funciono. Si lo solucionaba tenia la posibilidad de usar una pagina para t0do, y no tener que usar 3 paginas diferentes para modificar cada entidad :/
            if (service.searchPersona(matricula) instanceof Alumno) {
                Alumno alumno = (Alumno) service.searchPersona(matricula);
                alumno.setSalario(null);
                alumno.setType("Alumno");
                model.addAttribute("alumno", alumno);
                return "university/editAlumno";
            } else if (service.searchPersona(matricula) instanceof Profesor) {
                Profesor profesor = (Profesor) service.searchPersona(matricula);
                profesor.setType("Profesor");
                model.addAttribute("profesor", profesor);
                return "university/editProfesor";
            } else if (service.searchPersona(matricula) instanceof Intendente) {
                Intendente intendente = (Intendente) service.searchPersona(matricula);
                intendente.setCourse(null);
                intendente.setType("Intendente");
                model.addAttribute("intendente", intendente);
                return "university/editIntendente";
            }
            /*switch () {
                case "Alumno":
                    return "university/modificarPersona";
                case "Profesor":
                    return "university/modificarPersona";
                case "Intendente":
                    return "university/modificarPersona";
                default:
                    break;*/
        }
        return "redirect:/university/index";
    }

    @PostMapping(value = "/edit/{matricula}")
    public String editPersona(@ModelAttribute Alumno alumno, @PathVariable("matricula") Integer matricula) {
        if (service.savePersona(alumno)) {
            return "redirect:/university/index";
        }
        return "redirect:/university/index";
    }
}
