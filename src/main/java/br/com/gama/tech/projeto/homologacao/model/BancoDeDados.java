package br.com.gama.tech.projeto.homologacao.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.gama.tech.projeto.homologacao.negocio.Utilitario;

public class BancoDeDados {
	private Connection connection = null;
	private java.sql.Statement statement = null;
	private ResultSet resultset = null;

	// Método que conecta ao banco de dados:
	public void conectar() {

		// Paramêtros que são necessários para conectar ao BD.
		String servidor = "jdbc:mysql://localhost/b3tech_projeto_homologacao";
		String senha = "fsa41306";
		String usuario = "root";
		String driver = "com.mysql.cj.jdbc.Driver";

		// Tenta acessar ao banco caso não consiga, lança um exceção.
		try {
			Class.forName(driver);
			this.connection = DriverManager.getConnection(servidor, usuario, senha);
			this.statement = this.connection.createStatement();
		} catch (Exception e) {
			System.out.println("Erro no Servidor" + e.getMessage());
		}
	}

	// Método usado para ver se o Banco de Dados estão conectado ou não:
	public boolean estaConectado() {
		if (this.connection != null) {
			System.out.println("Conectado com êxito!");
			return true;
		} else {
			return false;
		}
	}

	// Método utilizado para Atualizar a tabela Cliente
	public void atualizaCliente(String numero_conta_cliente, String nome, String eMail, String telefone)
			throws SQLException {

		// Se o campo nome que foi passado como parâmetro estiver vazio, significa que
		// ele não foi
		// preenchido, portanto não precisa ser atualizado
		if (!nome.equals("")) {
			String query = "UPDATE clientes SET nome_cliente='" + nome + "' WHERE numero_conta=" + numero_conta_cliente
					+ ";";
			System.out.println(query);
			this.statement.executeUpdate(query);
		}

		// Se o campo E-Mail que foi passado como parâmetro estiver vazio, significa que
		// ele não foi
		// preenchido, portanto não precisa ser atualizado
		if (!eMail.equals("")) {
			String query = "UPDATE clientes SET email_cliente='" + eMail + "' WHERE numero_conta="
					+ numero_conta_cliente + ";";
			System.out.println(query);
			this.statement.executeUpdate(query);
		}

		// Se o telefon que foi passado como parâmetro estiver vazio, significa que ele
		// não foi
		// preenchido, portanto não precisa ser atualizado
		if (!telefone.equals("")) {
			String query = "UPDATE clientes SET celular_cliente='" + telefone + "' WHERE numero_conta="
					+ numero_conta_cliente + ";";
			System.out.println(query);
			this.statement.executeUpdate(query);
		}
	}

	// Método para Incluir as Contas, caso consiga incluir, retorna true, caso
	// conntrário false.
	public boolean incluirContas(String agencia_contas, String tipo_conta, String saldo_contas, String numero_contas) {
		boolean ret = false;
		try {
			String query = "INSERT INTO contas (numero_conta,numero_agencia,tipo_conta,saldo_conta) VALUES ('"
					+ numero_contas + "','" + agencia_contas + "','" + tipo_conta + "'," + saldo_contas + ");";

			System.out.println(query);
			this.statement.execute(query);
			System.out.println("Inserido com sucesso.");
			ret = true;
		} catch (SQLException e) {
			System.out.println("Erro ao Incluir...." + e.getMessage());
			return false;
		}
		return ret;
	}
	public int ultimaContaValida() {
		String query = "select * from contas";
		int conta = 0;
		try {
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			while(resultset.next()) {
				conta = resultset.getInt("numero_conta");
			}
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return conta;
	}
	public int incluirAgencia(String bairro, String cidade, String estado, String rua, String numero) {
		Utilitario ut = new Utilitario();
		int ret = 0;
		try {
			String query = "INSERT INTO agencias (bairro,cidade,estado,rua,numero) VALUES ('" + bairro + "','" + cidade
					+ "','" + estado + "','" + rua + "'," + numero + ");";
			String query1 = "SELECT numero_agencia FROM agencias;";

			System.out.println(query);
			this.statement.execute(query);
			System.out.println(query1);
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			System.out.println("Inserido com sucesso.");
			while (resultset.next()) {
				ret = resultset.getInt("numero_agencia");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao Incluir...." + e.getMessage());
			ret = -1;
		}
		return ret;
	}

	// Método para Incluir os Clientes, caso consiga incluir, retorna true, caso
	// conntrário false.
	public boolean incluirCliente(String nome, String cpf, int numeroConta, String telefone, String eMail,
			String agencia_conta) {
		try {
			String query = "INSERT INTO clientes (nome_cliente,cpf_cliente,numero_conta,celular_cliente,email_cliente,agencia_conta) VALUES ('"
					+ nome + "','" + cpf + "'," + numeroConta + ",'" + telefone + "','" + eMail + "'," + agencia_conta
					+ ");";

			System.out.println(query);
			this.statement.execute(query);
			System.out.println("Inserido com sucesso.");
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao Incluir...." + e.getMessage());
			return false;
		}
	}

	// Método para Excluir as Contas, caso consiga incluir, retorna true, caso
	// conntrário false.
	public boolean deletarConta(String id) {
		boolean deletou = false;
		try {
			String sql = "SELECT * FROM contas WHERE numero_conta=" + id + ";";
			this.resultset = this.statement.executeQuery(sql);
			while (resultset.next()) {
				deletou = true;
			}
			String query3 = "DELETE FROM operacoes WHERE numero_conta=" + id + ";";
			System.out.println(query3);
			this.statement.executeUpdate(query3);

			String query1 = "DELETE FROM contas WHERE numero_conta=" + id + ";";
			System.out.println(query1);
			this.statement.executeUpdate(query1);
			String query2 = "DELETE FROM clientes WHERE numero_conta=" + id + ";";
			System.out.println(query2);
			this.statement.executeUpdate(query2);

		} catch (SQLException e) {
			System.out.println("Erro ao deletar: " + e.getMessage());
		}
		return deletou;
	}

	// Método para consultar o CPF.
	// Retorna o CPF.
	public String consultaCPF(String cpf) {
		String query = "Select * From clientes where cpf_cliente like'%" + cpf + "%';";
		String meuCPF = "";
		try {
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			boolean fim = false;
			while (resultset.next()) {
				meuCPF = resultset.getString("nome_cliente");
				fim = true;
				return meuCPF;
			}
			// Condição que verifica se o CPF foi encontrado ou não.
			if (!resultset.next() && fim == false) {
				System.out.println("Não encontrado!");
				return "Não encontrado";
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
			return "Erro";
		}
		return meuCPF;
	}

	// Método para consultar o EMail.
	// Retorna o E-Mail.
	public String consultaEMail(String email) {
		String query = "Select * From clientes where cpf_cliente like'%" + email + "%';";
		String meuCPF = null;
		try {
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			boolean fim = false;
			while (resultset.next()) {
				meuCPF = resultset.getString("email_cliente");
				fim = true;
				return meuCPF;
			}
			// Condição que verifica se o E-Mail foi encontrado ou não.
			if (!resultset.next() && fim == false) {
				System.out.println("Não encontrado!");
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return "XXXXXXXXXXXXXX";

	}

	// Método para consultar o Telefone.
	// Retorna o Telefone.
	public String consultaTelefone(String telefone) {
		String query = "Select * From clientes where cpf_cliente like'%" + telefone + "%';";
		String meuCPF = null;
		String ret = "";
		try {
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			boolean fim = false;
			while (resultset.next()) {
				meuCPF = resultset.getString("celular_cliente");
				fim = true;
				return meuCPF;
			}
			// Condição que verifica se o telefone foi encontrado ou não.
			if (!resultset.next() && fim == false) {
				System.out.println("Não encontrado!");
				ret = "Não encontrado!";
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return ret;

	}

	// Método para consultar a agência.
	// Retorna a agência.
	public String consultaAgencia(String agencia) {
		String query1 = "Select * From clientes where cpf_cliente like'%" + agencia + "%';";
		String minhaConta = null;
		String minhaAgencia = null;
		try {
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			boolean fim = false;
			while (resultset.next()) {
				minhaConta = resultset.getString("numero_conta");
				fim = true;
				this.resultset = this.statement
						.executeQuery("Select * From contas where numero_conta=" + Integer.parseInt(minhaConta) + ";");
				this.statement = this.connection.createStatement();
				while (resultset.next()) {
					minhaAgencia = resultset.getString("numero_agencia");
					return minhaAgencia;
				}
			}

			// Condição que verifica se a agência foi encontrado ou não.
			if (!resultset.next() && fim == false) {
				System.out.println("Não encontrado!");
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return minhaAgencia;

	}
	public String listarAgencia() {
		String html = "<!DOCTYPE html>\r\n"
				+ "<html>\r\n"
				+ "\r\n"
				+ "<head>\r\n"
				+ "\r\n"
				+ "	<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css\" rel=\"stylesheet\"\r\n"
				+ "  integrity=\"sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC\" crossorigin=\"anonymous\">\r\n"
				+ "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>\r\n"
				+ "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js\"></script>\r\n"
				+ "  <script src=\"https://cdn.jsdelivr.net/gh/plentz/jquery-maskmoney@master/dist/jquery.maskMoney.min.js\"></script>\r\n"
				+ "  \r\n"
				+ "  <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css\">\r\n"
				+ "\r\n"
				+ "  <!-- Favicons -->\r\n"
				+ "  <link rel=\"apple-touch-icon\" href=\"/docs/5.0/assets/img/favicons/apple-touch-icon.png\" sizes=\"180x180\">\r\n"
				+ "  <link rel=\"icon\" href=\"/docs/5.0/assets/img/favicons/favicon-32x32.png\" sizes=\"32x32\" type=\"image/png\">\r\n"
				+ "  <link rel=\"icon\" href=\"/docs/5.0/assets/img/favicons/favicon-16x16.png\" sizes=\"16x16\" type=\"image/png\">\r\n"
				+ "  <link rel=\"manifest\" href=\"/docs/5.0/assets/img/favicons/manifest.json\">\r\n"
				+ "  <link rel=\"mask-icon\" href=\"/docs/5.0/assets/img/favicons/safari-pinned-tab.svg\" color=\"#7952b3\">\r\n"
				+ "  <link rel=\"icon\" href=\"/docs/5.0/assets/img/favicons/favicon.ico\">\r\n"
				+ "  <meta name=\"theme-color\" content=\"#7952b3\">\r\n"
				+ "\r\n"
				+ "  <title> Banco Foo Data </title>\r\n"
				+ "</head>";
		String query = "Select * from agencias";
		Utilitario ut = new Utilitario();
		try {
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			html += "<body><form action=/home/inserir/ method=post>";
			while(resultset.next()) {
				html += "Agencia: "+resultset.getInt("numero_agencia") + " Cidade: "+ ut.substituir(resultset.getString("cidade")) + " Bairro: " + ut.substituir(resultset.getString("bairro")) + " Estado: " + ut.substituir(resultset.getString("estado"))+"<br>";
				System.out.println(ut.substituir(resultset.getString("cidade")));
			}
			html += "<button input type=submit>Voltar</button></body></html>";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return html;
	}

	public String consultaTipoConta(String tipo) {
		String query1 = "Select * From clientes where cpf_cliente like'%" + tipo + "%';";
		String minhaConta = null;
		int tipoConta = -1;
		try {
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			System.out.println(query1);
			boolean fim = false;
			while (resultset.next()) {
				minhaConta = resultset.getString("numero_conta");
				fim = true;
				this.resultset = this.statement
						.executeQuery("Select * From contas where numero_conta=" + Integer.parseInt(minhaConta) + ";");
				this.statement = this.connection.createStatement();
				System.out.println("Select * From contas where numero_conta=" + Integer.parseInt(minhaConta) + ";");
				while (resultset.next()) {
					tipoConta = resultset.getInt("tipo_conta");
					// Transforma o valor retornado que é integer
					// Para uma string que corresponde ao tipo da popança.
					switch (tipoConta) {
					case 0:
						return "Conta_Corrente";
					case 1:
						return "Conta_Poupanca";
					case 2:
						return "Conta_Investimento";
					default:
						return "Conta_nao_encontrada";
					}
				}
			}

			// Condição que verifica se o tipo da conta foi encontrado ou não.

			if (!resultset.next() && fim == false) {
				System.out.println("Não encontrado!");
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return tipoConta + "";

	}

	// Método que retorna a conta
	public String consultaConta(String conta) {
		String query1 = "Select * From clientes where cpf_cliente like'%" + conta + "%';";
		String minhaConta = null;
		try {
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			boolean fim = false;
			while (resultset.next()) {
				minhaConta = resultset.getString("numero_conta");
				fim = true;
				return minhaConta;
			}
			// Verifica se a conta existe.
			if (!resultset.next() && fim == false) {
				System.out.println("Não encontrado!");
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return minhaConta;

	}
	public String consultarNome(String cpf, String tipo_conta) {
		String query1 = "Select * From clientes where cpf_cliente like'%" + cpf + "%';";
		String minhaConta = null;
		String minhaAgencia = null;
		String meuNome = null;
		try {
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			boolean fim = false;
			while (this.resultset.next()) {
				minhaConta = this.resultset.getString("numero_conta");
				meuNome = this.resultset.getString("nome_cliente");
				minhaAgencia = this.resultset.getString("agencia_conta");
				System.out.println(minhaConta + "===" + minhaAgencia);
				String query = "Select * from contas Where (numero_conta=" + minhaConta + " AND numero_agencia="
						+ minhaAgencia + " AND tipo_conta=" + tipo_conta + ");";
				System.out.println("Busca......"+query);
				this.resultset = this.statement.executeQuery(query);
				this.statement = this.connection.createStatement();
				while (this.resultset.next()) {
					fim = true;
					return meuNome;
				}
			}
			// Verifica se a conta existe.
			if (!resultset.next() && fim == false) {
				System.out.println("Não encontrado!");
				return "Não encontrado";
			}
		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return meuNome;

	}

	// Método que faz a aplicação.
	public boolean aplicar(String agencia, String numero_conta, String valor) {
		String query1 = "SELECT * FROM contas WHERE numero_conta=" + numero_conta + ";";
		boolean aplicou = false;
		try {
			// Primeiro ele pagor que tem na conta
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			while (resultset.next()) {
				// Faz a soma do valor sacado no saldo.
				int valorAtualizado = Integer.parseInt(valor) + resultset.getInt("saldo_conta");
				String query2 = "UPDATE contas SET saldo_conta=" + valorAtualizado + " where numero_conta="
						+ numero_conta + ";";
				this.statement.executeUpdate(query2);
				String query3 = "INSERT INTO operacoes(tipo_operacao,saldo,descricao,numero_conta,agencia_conta) VALUES(1,"
						+ valorAtualizado + "," + "'Deposito realizado " + valor + "'," + numero_conta + "," + agencia
						+ ");";
				System.out.println(query3);
				this.statement.executeUpdate(query3);
				aplicou = true;
			}

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return aplicou;

	}

	// Método que faz o saque.
	public boolean retirada(String agencia, String numero_conta, String valor) {
		String query1 = "SELECT * FROM contas WHERE numero_conta=" + numero_conta + " AND numero_agencia=" + agencia
				+ ";";
		System.out.println(query1);
		boolean aplicou = false;
		try {
			// Primeiro ele pagor que tem na conta
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			while (resultset.next()) {
				// Verifica se o saldo é suficiente para fazer o saque.
				if (Integer.parseInt(valor) <= resultset.getInt("saldo_conta")) {
					// Faz a subtração do valor sacado no saldo.
					int valorAtualizado = resultset.getInt("saldo_conta") - Integer.parseInt(valor);
					String query2 = "UPDATE contas SET saldo_conta=" + valorAtualizado + " where numero_conta="
							+ numero_conta + " AND numero_agencia=" + agencia + ";";
					this.statement.executeUpdate(query2);
					String query3 = "INSERT INTO operacoes(tipo_operacao,saldo,descricao,numero_conta,agencia_conta) VALUES(0,"
							+ valorAtualizado + "," + "'Saque realizado " + valor + "'," + numero_conta + "," + agencia
							+ ");";
					System.out.println(query3);
					this.statement.executeUpdate(query3);
					aplicou = true;
				}
			}

		} catch (SQLException e) {
			System.out.println("Erro: " + e.getMessage());
		}
		return aplicou;

	}

	public String extrato(String numero_conta) {
		String query = "SELECT * FROM operacoes WHERE numero_conta=" + numero_conta + ";";
		String query1 = "SELECT * FROM clientes WHERE numero_conta=" + numero_conta + ";";
		String html = "";
		try {
			this.resultset = this.statement.executeQuery(query1);
			this.statement = this.connection.createStatement();
			System.out.println(query1);
			while (this.resultset.next()) {
				html = "<!DOCTYPE html>" + "<html>" + "<head>" + ""
						+ "				<link href=https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css rel=stylesheet"
						+ "				integrity=sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC crossorigin=anonymous>"
						+ "	"
						+ "				<script src=https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js></script>"
						+ "				<script src=https://cdnjs.cloudflare.com/ajax/libs/jquery.mask/1.14.11/jquery.mask.min.js></script>"
						+ "		"
						+ "				<link rel=stylesheet href=https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css>"
						+ "			" + "				<!-- Favicons -->"
						+ "				<link rel=apple-touch-icon href=/docs/5.0/assets/img/favicons/apple-touch-icon.png sizes=180x180>"
						+ "				<link rel=icon href=/docs/5.0/assets/img/favicons/favicon-32x32.png sizes=32x32 type=image/png>"
						+ "				<link rel=icon href=/docs/5.0/assets/img/favicons/favicon-16x16.png sizes=16x16 type=image/png>"
						+ "				<link rel=manifest href=/docs/5.0/assets/img/favicons/manifest.json>"
						+ "				<link rel=mask-icon href=/docs/5.0/assets/img/favicons/safari-pinned-tab.svg color=#7952b3>"
						+ "				<link rel=icon href=/docs/5.0/assets/img/favicons/favicon.ico>"
						+ "				<meta name=theme-color content=#7952b3>" + "			"
						+ "				<title> Banco Foo Data</title>" + "				" + "				+ <!--    "
						+ "				+ " + "numero da conta" + "				ag" + "				desc "
						+ "				saldo" + "				-->" + "				" + "				</head>"
						+ "				" + "	<body class=text-center bg-light text-dark>" + "				"
						+ "				<div class=container>" + "<form action=/home/ method=post>"
						+ "					<main>" + "						<div class=py-5 text-center>"
						+ "							<div class=container text-center>"
						+ "								<h2 class=text-uppercase>Confira seu extrato!</h2>"
						+ "							</div>"
						+ "							<div class=mx-auto text-uppercase style=width: 200px;>Insira sua conta</div>"
						+ "							<br />" + "							<div class=col-sm-4>" + ""
						+ "								<label for=name class=form-label> Nome Completo</label>"
						+ "								<input type=text class=form-control id=Nome placeholder=pegar do bd value="
						+ resultset.getString("nome_cliente") + " disabled>" + "							</div>"
						+ "							<hr class=my-4>" + "	";
			}
			String query3 = "SELECT * FROM contas WHERE numero_conta=" + numero_conta + ";";
			System.out.println(query3);
			this.resultset = this.statement.executeQuery(query3);
			this.statement = this.connection.createStatement();
			while (this.resultset.next()) {
				html += "" + "								<div class=form-group row>"
						+ "									<label>Agencia</label><br>" + ""
						+ "										<input type=text id=agencia value="
						+ resultset.getInt("numero_agencia") + " align=center disabled>"
						+ "									</div>"
						+ "									<label>Conta</label><br>" + ""
						+ "										<input type=text id=conta value="
						+ resultset.getString("numero_conta") + " align=center disabled>"
						+ "									</div>" + "								</div>" + "" + "";
			}
			this.resultset = this.statement.executeQuery(query);
			this.statement = this.connection.createStatement();
			System.out.println(query);
			int contador = 1;
			html += "							<div class=col-sm-7>"
					+ "<label for=desc class=form-label>Historico Operacoes</label>";
			while (resultset.next()) {
				String tipo_operacao = "";
				if (resultset.getString("tipo_operacao").equals("0")) {
					tipo_operacao = "Sacar";
				} else if (resultset.getString("tipo_operacao").equals("1")) {
					tipo_operacao = "Depositar";
				}
				html += "<br> Operacao " + contador
						+ "<br>Tipo Operacao = <input type=text class=form-control id=desc placeholder=pegar do bd value="
						+ resultset.getString("descricao").replace(" ", "_") + " disabled>"
						+ "Valor do Saldo na Conta = <input type=text class=form-control id=desc placeholder=pegar do bd value="
						+ resultset.getString("saldo") + " disabled>"
						+ "Tipo Operacao <input type=text class=form-control id=desc placeholder=pegar do bd value="
						+ tipo_operacao + " disabled>";
				contador++;
			}
			html += "<hr class=my-4>	<br />" + "							<input type=submit value=Voltar>"
					+ "</form>" + "" + "						</div>" + "					</main>"
					+ "				</div>"
					+ "				<script src=https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
					+ "				integrity=sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM crossorigin=anonymous></script>"
					+ "</body>" + "</html>" + "";

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(html);
		return html;
	}
}