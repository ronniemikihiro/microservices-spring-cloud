package br.com.api.user;

import br.com.domain.entity.dto.EmpresaDTO;
import br.com.domain.entity.dto.PerfilDTO;
import br.com.domain.entity.dto.PermissaoDTO;
import br.com.domain.entity.dto.UsuarioDTO;
import br.com.domain.enums.EnumTipoAutenticacao;
import br.com.domain.exception.errors.ErrorException;
import br.com.domain.repository.EmpresaRepository;
import br.com.domain.repository.PerfilRepository;
import br.com.domain.repository.PermissaoRepository;
import br.com.domain.repository.UsuarioRepository;
import com.google.common.collect.Lists;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaRepositories({"br.com.domain.repository"})
@EntityScan({"br.com.domain.entity"})
@ComponentScan("br.com")
public class ApiUserApplication /*extends SpringBootServletInitializer*/ {

	public static void main(String[] args) {
		SpringApplication.run(ApiUserApplication.class, args);
	}

//	@Override
//	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//		return application.sources(RestUserApplication.class);
//	}

	/**
	 * Inicia alguns atributos no banco de dados.
	 *
	 * @param usuarioRepository
	 * @param permissaoRepository
	 * @param perfilRepository
	 * @param empresaRepository
	 * @return CommandLineRunner
	 */
	@Bean
	CommandLineRunner init(UsuarioRepository usuarioRepository,
						   PermissaoRepository permissaoRepository,
						   PerfilRepository perfilRepository,
						   EmpresaRepository empresaRepository) {
		return args -> {
			createPermissoes(permissaoRepository);
			createPerfis(perfilRepository, permissaoRepository);
			createEmpresas(empresaRepository);
			createUsuarios(usuarioRepository, empresaRepository, perfilRepository, new BCryptPasswordEncoder());
		};
	}

	private void createPermissoes(PermissaoRepository permissaoRepository) throws ErrorException {
		PermissaoDTO permissaoDTO = permissaoRepository.obterPorId(1L);

		if (permissaoDTO == null) {
			permissaoDTO = PermissaoDTO.builder()
					.nome("Inserir")
					.codigo("I")
					.action("/post")
					.build();

			permissaoRepository.save(permissaoDTO.toEntidade());
		}

		permissaoDTO = permissaoRepository.obterPorId(2L);

		if (permissaoDTO == null) {
			permissaoDTO = PermissaoDTO.builder()
					.nome("Alterar")
					.codigo("A")
					.action("/put")
					.build();

			permissaoRepository.save(permissaoDTO.toEntidade());
		}

		permissaoDTO = permissaoRepository.obterPorId(3L);

		if (permissaoDTO == null) {
			permissaoDTO = PermissaoDTO.builder()
					.nome("Excluir")
					.codigo("E")
					.action("/delete")
					.build();

			permissaoRepository.save(permissaoDTO.toEntidade());
		}
	}

	private void createPerfis(PerfilRepository perfilRepository, PermissaoRepository permissaoRepository) throws ErrorException {
		PerfilDTO perfilDTO = perfilRepository.obterPorId(1L);

		if (perfilDTO == null) {
			perfilDTO = PerfilDTO.builder()
					.nome("Administrador")
					.permissoes(permissaoRepository.listarTodos())
					.build();

			perfilRepository.save(perfilDTO.toEntidade());
		}

		perfilDTO = perfilRepository.obterPorId(2L);

		if (perfilDTO == null) {
			perfilDTO = PerfilDTO.builder()
					.nome("Usuário")
					.permissoes(Lists.newArrayList(permissaoRepository.obterPorId(1L)))
					.build();

			perfilRepository.save(perfilDTO.toEntidade());
		}
	}

	private void createEmpresas(EmpresaRepository empresaRepository) throws ErrorException {
		EmpresaDTO empresaDTO = empresaRepository.obterPorId(1L);

		if (empresaDTO == null) {
			empresaDTO = EmpresaDTO.builder()
					.nome("Empresa de Tecnologia Ltda")
					.conta("Caixa")
					.username("tecnologia")
					.password("teste")
					.caixa("Empresa muito rica...")
					.build();

			empresaRepository.save(empresaDTO.toEntidade());
		}

		empresaDTO = empresaRepository.obterPorId(2L);

		if (empresaDTO == null) {
			empresaDTO = EmpresaDTO.builder()
					.nome("Empresa de Desenvolvimento de software Ltda")
					.conta("Itaú")
					.username("software")
					.password("teste")
					.caixa("Empresa pobre, em falência...")
					.build();

			empresaRepository.save(empresaDTO.toEntidade());
		}
	}

	private void createUsuarios(UsuarioRepository usuarioRepository, EmpresaRepository empresaRepository, PerfilRepository perfilRepository, BCryptPasswordEncoder bCryptPasswordEncoder) throws ErrorException {
		UsuarioDTO usuarioDTO = usuarioRepository.obterPorId(1L);

		if (usuarioDTO == null) {
			usuarioDTO = UsuarioDTO.builder()
					.nome("Administrador")
					.username("admin")
					.senha(bCryptPasswordEncoder.encode("teste"))
					.setor("Diretoria")
					.cargo("Presidente")
					.dominio("empresaAdministrador")
					.tipoAutenticacao(EnumTipoAutenticacao.LDAP)
					.empresa(empresaRepository.obterPorId(1L))
					.perfil(perfilRepository.obterPorId(1L))
					.build();

			usuarioRepository.save(usuarioDTO.toEntidade());
		}

		usuarioDTO = usuarioRepository.obterPorId(2L);

		if (usuarioDTO == null) {
			usuarioDTO = UsuarioDTO.builder()
					.nome("Ronnie Mikihiro Sato Lopes")
					.username("ronnie")
					.senha(bCryptPasswordEncoder.encode("teste"))
					.setor("Desenvolvimento")
					.cargo("Desenvolveldor de Software")
					.dominio("empresaDesenvolvedor")
					.tipoAutenticacao(EnumTipoAutenticacao.SO)
					.empresa(empresaRepository.obterPorId(2L))
					.perfil(perfilRepository.obterPorId(2L))
					.build();

			usuarioRepository.save(usuarioDTO.toEntidade());
		}
	}

}
