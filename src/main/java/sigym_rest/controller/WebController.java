package sigym_rest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import sigym_rest.dto.MembresiaDto;
import sigym_rest.dto.PagoDto;
import sigym_rest.dto.ProspectoDto;
import sigym_rest.dto.ServicioDto;
import sigym_rest.service.MembresiaService;
import sigym_rest.service.PagoService;
import sigym_rest.service.ProspectoService;
import sigym_rest.service.ServicioService;
import sigym_rest.dto.UsuarioDto;
import sigym_rest.service.UsuarioService;

@Controller
public class WebController {

    private final ProspectoService prospectoService;
    private final MembresiaService membresiaService;
    private final ServicioService servicioService;
    private final PagoService pagoService;
    private final UsuarioService usuarioService;

    public WebController(
            ProspectoService prospectoService,
            MembresiaService membresiaService,
            ServicioService servicioService,
            PagoService pagoService,
            UsuarioService usuarioService) {

        this.prospectoService = prospectoService;
        this.membresiaService = membresiaService;
        this.servicioService = servicioService;
        this.pagoService = pagoService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String inicio() {
        return "inicio";
    }

    @GetMapping("/web/prospectos")
    public String prospectos(Model model) {

        model.addAttribute(
                "prospectos",
                prospectoService.findAll()
        );

        return "prospectos";
    }

    @GetMapping("/web/prospectos/nuevo")
    public String nuevoProspecto(Model model) {

        model.addAttribute(
                "prospecto",
                new ProspectoDto()
        );

        return "prospecto-form";
    }

    @PostMapping("/web/prospectos/guardar")
    public String guardarProspecto(
            @ModelAttribute ProspectoDto prospectoDto) {

        prospectoService.save(prospectoDto);

        return "redirect:/web/prospectos";
    }

    @GetMapping("/web/prospectos/editar/{id}")
    public String editarProspecto(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "prospecto",
                prospectoService.findById(id)
        );

        return "prospecto-form";
    }

    @PostMapping("/web/prospectos/actualizar/{id}")
    public String actualizarProspecto(
            @PathVariable Long id,
            @ModelAttribute ProspectoDto prospectoDto) {

        prospectoService.update(id, prospectoDto);

        return "redirect:/web/prospectos";
    }

    @PostMapping("/web/prospectos/eliminar/{id}")
    public String eliminarProspecto(
            @PathVariable Long id) {

        prospectoService.delete(id);

        return "redirect:/web/prospectos";
    }

    @GetMapping("/web/membresias")
    public String membresias(Model model) {

        model.addAttribute(
                "membresias",
                membresiaService.findAll()
        );

        return "membresias";
    }

    @GetMapping("/web/membresias/nueva")
    public String nuevaMembresia(Model model) {

        model.addAttribute(
                "membresia",
                new MembresiaDto()
        );

        model.addAttribute(
                "prospectos",
                prospectoService.findAll()
        );

        model.addAttribute(
                "servicios",
                servicioService.findAll()
        );

        return "membresia-form";
    }

    @PostMapping("/web/membresias/guardar")
    public String guardarMembresia(
            @ModelAttribute MembresiaDto membresiaDto) {

        membresiaService.save(membresiaDto);

        return "redirect:/web/membresias";
    }

    @GetMapping("/web/membresias/editar/{id}")
    public String editarMembresia(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "membresia",
                membresiaService.findById(id)
        );

        model.addAttribute(
                "prospectos",
                prospectoService.findAll()
        );

        model.addAttribute(
                "servicios",
                servicioService.findAll()
        );

        return "membresia-form";
    }

    @PostMapping("/web/membresias/actualizar/{id}")
    public String actualizarMembresia(
            @PathVariable Long id,
            @ModelAttribute MembresiaDto membresiaDto) {

        membresiaService.update(id, membresiaDto);

        return "redirect:/web/membresias";
    }

    @PostMapping("/web/membresias/eliminar/{id}")
    public String eliminarMembresia(
            @PathVariable Long id) {

        membresiaService.delete(id);

        return "redirect:/web/membresias";
    }

    @GetMapping("/web/servicios")
    public String servicios(Model model) {

        model.addAttribute(
                "servicios",
                servicioService.findAll()
        );

        return "servicios";
    }

    @GetMapping("/web/servicios/nuevo")
    public String nuevoServicio(Model model) {

        model.addAttribute(
                "servicio",
                new ServicioDto()
        );

        return "servicio-form";
    }

    @PostMapping("/web/servicios/guardar")
    public String guardarServicio(
            @ModelAttribute ServicioDto servicioDto) {

        servicioService.save(servicioDto);

        return "redirect:/web/servicios";
    }

    @GetMapping("/web/servicios/editar/{id}")
    public String editarServicio(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "servicio",
                servicioService.findById(id)
        );

        return "servicio-form";
    }

    @PostMapping("/web/servicios/actualizar/{id}")
    public String actualizarServicio(
            @PathVariable Long id,
            @ModelAttribute ServicioDto servicioDto) {

        servicioService.update(id, servicioDto);

        return "redirect:/web/servicios";
    }

    @PostMapping("/web/servicios/eliminar/{id}")
    public String eliminarServicio(
            @PathVariable Long id) {

        servicioService.delete(id);

        return "redirect:/web/servicios";
    }

    @GetMapping("/web/pagos")
    public String pagos(Model model) {

        model.addAttribute(
                "pagos",
                pagoService.findAll()
        );

        return "pagos";
    }

    @GetMapping("/web/pagos/nuevo")
    public String nuevoPago(Model model) {

        model.addAttribute(
                "pago",
                new PagoDto()
        );

        model.addAttribute(
                "membresias",
                membresiaService.findAll()
        );

        return "pago-form";
    }

    @PostMapping("/web/pagos/guardar")
    public String guardarPago(
            @ModelAttribute PagoDto pagoDto) {

        pagoService.save(pagoDto);

        return "redirect:/web/pagos";
    }

    @GetMapping("/web/pagos/editar/{id}")
    public String editarPago(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "pago",
                pagoService.findById(id)
        );

        model.addAttribute(
                "membresias",
                membresiaService.findAll()
        );

        return "pago-form";
    }

    @PostMapping("/web/pagos/actualizar/{id}")
    public String actualizarPago(
            @PathVariable Long id,
            @ModelAttribute PagoDto pagoDto) {

        pagoService.update(id, pagoDto);

        return "redirect:/web/pagos";
    }

    @PostMapping("/web/pagos/eliminar/{id}")
    public String eliminarPago(
            @PathVariable Long id) {

        pagoService.delete(id);

        return "redirect:/web/pagos";
    }

    @GetMapping("/web/usuarios")
    public String usuarios(Model model) {

        model.addAttribute(
                "usuarios",
                usuarioService.findAll()
        );

        return "usuarios";
    }

    @GetMapping("/web/usuarios/nuevo")
    public String nuevoUsuario(Model model) {

        UsuarioDto usuario = new UsuarioDto();
        usuario.setActivo(true);
        usuario.setRol("USER");

        model.addAttribute(
                "usuario",
                usuario
        );

        return "usuario-form";
    }

    @PostMapping("/web/usuarios/guardar")
    public String guardarUsuario(
            @ModelAttribute UsuarioDto usuarioDto) {

        usuarioService.save(usuarioDto);

        return "redirect:/web/usuarios";
    }

    @GetMapping("/web/usuarios/editar/{id}")
    public String editarUsuario(
            @PathVariable Long id,
            Model model) {

        model.addAttribute(
                "usuario",
                usuarioService.findById(id)
        );

        return "usuario-form";
    }

    @PostMapping("/web/usuarios/actualizar/{id}")
    public String actualizarUsuario(
            @PathVariable Long id,
            @ModelAttribute UsuarioDto usuarioDto) {

        usuarioService.update(id, usuarioDto);

        return "redirect:/web/usuarios";
    }

    @PostMapping("/web/usuarios/eliminar/{id}")
    public String eliminarUsuario(
            @PathVariable Long id) {

        usuarioService.delete(id);

        return "redirect:/web/usuarios";
    }


}
