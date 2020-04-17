package com.teamwork.cineperu.negocio;

import com.teamwork.cineperu.bean.BeanButaca;
import com.teamwork.cineperu.bean.BeanHorario;
import com.teamwork.cineperu.bean.BeanMontoPago;
import com.teamwork.cineperu.entidad.Butaca;
import com.teamwork.cineperu.entidad.EstadoButaca;
import com.teamwork.cineperu.entidad.Horario;
import com.teamwork.cineperu.entidad.Pelicula;
import com.teamwork.cineperu.entidad.UsuarioToken;
import com.teamwork.cineperu.entidad.request.GetListButacaRequest;
import com.teamwork.cineperu.entidad.request.GetListHorarioRequest;
import com.teamwork.cineperu.entidad.request.GetMontoPagoRequest;
import com.teamwork.cineperu.entidad.request.RealizarPagoRequest;
import com.teamwork.cineperu.entidad.request.UserTokenRequest;
import com.teamwork.cineperu.entidad.response.GetListButacaResponse;
import com.teamwork.cineperu.entidad.response.GetListHorarioResponse;
import com.teamwork.cineperu.entidad.response.GetListMovieResponse;
import com.teamwork.cineperu.entidad.response.GetMontoPagoResponse;
import com.teamwork.cineperu.entidad.response.RealizarPagoResponse;
import com.teamwork.cineperu.repositorio.ButacaRepositorio;
import com.teamwork.cineperu.repositorio.HorarioRepositorio;
import com.teamwork.cineperu.repositorio.PeliculaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PeliculaNegocio {

	@Autowired
	private PeliculaRepositorio peliculaRepositorio;
	@Autowired
	private UsuarioTokenNegocio usuarioTokenNegocio;
	@Autowired
	private HorarioRepositorio horarioRepositorio;
	@Autowired
	private ButacaRepositorio butacaRepositorio;

	public GetListMovieResponse listaPelicula(UserTokenRequest userTokenRequest) {
		GetListMovieResponse getListMovieResponse = new GetListMovieResponse();
		getListMovieResponse.setErrorCode(0);
		getListMovieResponse.setErrorMessage("");
		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(userTokenRequest.getToken());
			if (usuarioToken == null) {
				getListMovieResponse.setErrorCode(100);
				getListMovieResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
				return getListMovieResponse;
			}

			List<Pelicula> listaPelicula = (List<Pelicula>) peliculaRepositorio.findAll();
			getListMovieResponse.setLista(listaPelicula);
		} catch (Exception ex) {
			getListMovieResponse.setErrorCode(0);
			getListMovieResponse.setErrorMessage("Error en procesos");
			ex.printStackTrace();
		}
		return getListMovieResponse;
	}

	public GetListHorarioResponse listaHorario(GetListHorarioRequest getListHorarioRequest) {
		GetListHorarioResponse getListHorarioResponse = new GetListHorarioResponse();
		getListHorarioResponse.setErrorCode(0);
		getListHorarioResponse.setErrorMessage("");
		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(getListHorarioRequest.getToken());
			if (usuarioToken == null) {
				getListHorarioResponse.setErrorCode(100);
				getListHorarioResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
				return getListHorarioResponse;
			}

			List<BeanHorario> listaBeanHorario = new ArrayList<>();
			List<Horario> listaHorario = (List<Horario>) horarioRepositorio.buscarPorPelicula(
					getListHorarioRequest.getCodigoCine(), getListHorarioRequest.getCodigoPelicula());

			for (Horario horario : listaHorario) {

				BeanHorario beanHorario = new BeanHorario();
				beanHorario.setCodigoHorario(horario.getCodigoHorario());
				beanHorario.setDescripcionHorario(horario.getDescripcion());
				beanHorario.setCodigoSala(horario.getSala().getCodigoSala());
				beanHorario.setNombreSala(horario.getSala().getNombre());

				listaBeanHorario.add(beanHorario);

			}

			getListHorarioResponse.setLista(listaBeanHorario);

		} catch (Exception ex) {
			getListHorarioResponse.setErrorCode(0);
			getListHorarioResponse.setErrorMessage("Error en procesos");
			ex.printStackTrace();
		}
		return getListHorarioResponse;
	}

	public GetListButacaResponse listaButaca(GetListButacaRequest getListButacaRequest) {
		GetListButacaResponse getListButacaResponse = new GetListButacaResponse();
		getListButacaResponse.setErrorCode(0);
		getListButacaResponse.setErrorMessage("");
		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(getListButacaRequest.getToken());
			if (usuarioToken == null) {
				getListButacaResponse.setErrorCode(100);
				getListButacaResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
				return getListButacaResponse;
			}

			List<BeanButaca> listaBeanButaca = new ArrayList<>();
			List<Butaca> listaButaca = (List<Butaca>) butacaRepositorio
					.buscarPorSala(getListButacaRequest.getCodigoSala());

			for (Butaca butaca : listaButaca) {

				BeanButaca beanButaca = new BeanButaca();
				beanButaca.setCodigoButaca(butaca.getCodigoButaca());
				beanButaca.setNombreButaca(butaca.getNombre());
				beanButaca.setFilaButaca(butaca.getFila());
				beanButaca.setCodigoEstadoButaca(butaca.getEstadoButaca().getCodigoEstadoButaca());
				beanButaca.setNombreEstadoButaca(butaca.getEstadoButaca().getNombre());

				listaBeanButaca.add(beanButaca);

			}

			getListButacaResponse.setLista(listaBeanButaca);

		} catch (Exception ex) {
			getListButacaResponse.setErrorCode(0);
			getListButacaResponse.setErrorMessage("Error en procesos");
			ex.printStackTrace();
		}
		return getListButacaResponse;
	}

	public GetMontoPagoResponse consultarMontoPago(GetMontoPagoRequest getMontoPagoRequest) {
		GetMontoPagoResponse getMontoPagoResponse = new GetMontoPagoResponse();
		getMontoPagoResponse.setErrorCode(0);
		getMontoPagoResponse.setErrorMessage("");
		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(getMontoPagoRequest.getToken());
			if (usuarioToken == null) {
				getMontoPagoResponse.setErrorCode(100);
				getMontoPagoResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
				return getMontoPagoResponse;
			}

			List<Butaca> listaButaca = (List<Butaca>) butacaRepositorio
					.buscarPorSala(getMontoPagoRequest.getCodigoSala());

			for (Butaca butaca : listaButaca.stream()
					.filter(p -> getMontoPagoRequest.getButacas().contains(p.getCodigoButaca()))
					.collect(Collectors.toList())) {

				if (butaca.getEstadoButaca().getCodigoEstadoButaca() != 1) {
					getMontoPagoResponse.setErrorCode(201);
					getMontoPagoResponse.setErrorMessage("El asiento " + butaca.getNombre() + " en la fila "
							+ butaca.getFila() + " ya no se encuentra disponible");
					return getMontoPagoResponse;
				}

			}
			
			double precio = 15.00;

			BeanMontoPago montoPago = new BeanMontoPago();
			montoPago.setSubTotal(precio * getMontoPagoRequest.getButacas().size());
			montoPago.setIgv(montoPago.getSubTotal() * 0.18);
			montoPago.setTotal(montoPago.getSubTotal() + montoPago.getIgv());
			getMontoPagoResponse.setMontoPago(montoPago);

		} catch (Exception ex) {
			getMontoPagoResponse.setErrorCode(0);
			getMontoPagoResponse.setErrorMessage("Error en procesos");
			ex.printStackTrace();
		}
		return getMontoPagoResponse;
	}
	
	public RealizarPagoResponse registrarPago(RealizarPagoRequest realizarPagoRequest) {
		RealizarPagoResponse realizarPagoResponse = new RealizarPagoResponse();
		realizarPagoResponse.setErrorCode(0);
		realizarPagoResponse.setErrorMessage("");
		try {
			UsuarioToken usuarioToken = usuarioTokenNegocio.obtenerUsuarioToken(realizarPagoRequest.getToken());
			if (usuarioToken == null) {
				realizarPagoResponse.setErrorCode(100);
				realizarPagoResponse.setErrorMessage("Credencial de acceso vencida o incorrecta");
				return realizarPagoResponse;
			}

			List<Butaca> listaButaca = (List<Butaca>) butacaRepositorio
					.buscarPorSala(realizarPagoRequest.getCodigoSala());

			for (Butaca butaca : listaButaca.stream()
					.filter(p -> realizarPagoRequest.getButacas().contains(p.getCodigoButaca()))
					.collect(Collectors.toList())) {

				if (butaca.getEstadoButaca().getCodigoEstadoButaca() != 1) {
					realizarPagoResponse.setErrorCode(201);
					realizarPagoResponse.setErrorMessage("El asiento " + butaca.getNombre() + " en la fila "
							+ butaca.getFila() + " ya no se encuentra disponible");
					return realizarPagoResponse;
				}

				// CAMBIAMOS EL ESTADO A PAGADO
				EstadoButaca estadoButaca = new EstadoButaca();
				estadoButaca.setCodigoEstadoButaca(new Long(2));
				butaca.setEstadoButaca(estadoButaca);
				butacaRepositorio.save(butaca);
			}
			
			double precio = 15.00;

			BeanMontoPago montoPago = new BeanMontoPago();
			montoPago.setSubTotal(precio * realizarPagoRequest.getButacas().size());
			montoPago.setIgv(montoPago.getSubTotal() * 0.18);
			montoPago.setTotal(montoPago.getSubTotal() + montoPago.getIgv());
			realizarPagoResponse.setMontoPago(montoPago);

		} catch (Exception ex) {
			realizarPagoResponse.setErrorCode(0);
			realizarPagoResponse.setErrorMessage("Error en procesos");
			ex.printStackTrace();
		}
		return realizarPagoResponse;
	}
	
}
