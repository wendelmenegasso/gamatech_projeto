<!DOCTYPE html>
<html>
<head>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!-- Favicons -->
<link rel="apple-touch-icon" href="/docs/5.0/assets/img/favicons/apple-touch-icon.png" sizes="180x180">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-32x32.png" sizes="32x32" type="image/png">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon-16x16.png" sizes="16x16" type="image/png">
<link rel="manifest" href="/docs/5.0/assets/img/favicons/manifest.json">
<link rel="mask-icon" href="/docs/5.0/assets/img/favicons/safari-pinned-tab.svg" color="#7952b3">
<link rel="icon" href="/docs/5.0/assets/img/favicons/favicon.ico">
<meta name="theme-color" content="#7952b3">
<title> Banco </title>
</head>
<body class="text-center bg-light text-dark">
<div class="container">
<form action="/home/consultar/" method="post">

  <main>
    <div class="py-5 text-center">
    	<div class="container text-center">
		<h2>Abra sua conta!</h2>
	</div>
    	<div class="mx-auto" style="width: 200px;">Insira seus dados</div>
    	<br />
    		  <div class="col-sm-7">
              <label for="name" class="form-label">Nome Completo</label>
              
              <input type="text" class="form-control" name="nome_cliente" placeholder="" value="" th:field="*{nome_cliente}" required>
              <div class="invalid-feedback">
                ? necess?rio um nome v?lido
              </div>
          		</div>
              <br />
              <div class="col-sm-4">
              <label for="name" class="form-label">CPF</label>
              <input type="text" class="form-control" name="cpf_cliente" placeholder="" value="" required>
              <div class="invalid-feedback">
                ? necess?rio um CPF v?lido
              </div>
          </div>
   		<br />
          <div class="col-md-7 col-lg-8">
          <div class="row g-3">
            <div class="col-sm-6">
              <label for="firstName" class="form-label">E-mail</label>
              <input type="text" class="form-control" name="email_contato" placeholder="" value="" required>
              <div class="invalid-feedback">
                ? necess?rio um e-mail v?lido
              </div>
            </div>

            <div class="col-sm-4">
              <label for="lastName" class="form-label">Telefone</label>
              <input type="text" class="form-control" name="telefone_contato" placeholder="" value="" required>
              <div class="invalid-feedback">
                ? necess?rio um e-mail v?lido
              </div>
            </div>
        </div>
    </div>

    <hr class="my-4">

    <div class="col-md-10 col-lg-12">
          <div class="row g-3">
            <div class="col-sm-4">
              <label for="firstName" class="form-label">Agencia</label>
              <input type="text" class="form-control" name="agencia_conta" placeholder="" value="" required>
              <div class="invalid-feedback">
                ? necess?rio uma agencia v?lida
              </div>
            </div>
            <div class="col-sm-4">
              <label for="lastName" class="form-label">Numero Conta</label>
              <input type="text" class="form-control" name="numero_conta" placeholder="" value="" required>
      
              <div class="invalid-feedback">
					Deposito inicial invalido
              </div>
              </div>
        </div>
        <br>
        <select class="form-select" name="tipo_conta" aria-label="selecione o tipo de conta">
  <option selected>Selecione o tipo da conta</option>
  <option value="0">Conta Corrente</option>
  <option value="1">Poupanca</option>
  <option value="2">Investimento</option>
</select>
    </div>
    <br />
    <input type="submit" value="Consultar"/>
    
</form>          	
            
</body>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
</html>