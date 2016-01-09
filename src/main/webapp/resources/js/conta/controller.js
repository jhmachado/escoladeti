function ContaController($scope, $http, $routeParams) {
    $scope.novo = function() {
        $scope.getPerfisDeAcesso();
        if ($routeParams.ContaId) {
            $scope.carregarConta();
        } else {
            $scope.Conta = getNovoConta();
           // console.log(angular.toJson($scope.Conta, true));
            window.location = '#/cadastroConta';
        }
    };
    $scope.editar = function(Conta) {
        console.log('Editar Conta');
        window.location = '#/cadastroConta/' + Conta.id;
    };
    $scope.voltar = function () {
        window.location = '#/listaConta';
    };
    
    $scope.getTodos = function(numeroPagina) {
        console.log(numeroPagina);
        $http.get('./rest/ContaSource/listar/pag/' + numeroPagina)
            .success(function(Contas) {
                $scope.pagina = Contas;
                console.log('Contas ' + Contas);
            }).error(function(data) {
                console.log('erro ao buscar Contas ' + data);
            });
    };
    
    $scope.buscaContasContendoNome = function() {
        console.log($scope.busca);
        if(!$scope.busca.empty){
        $http.get('./rest/ContaSource/Contas?q=' + $scope.busca.toUpperCase())
            .then(function(Contas) {
                console.log(Contas.data.list);
                $scope.pagina = Contas.data;
            });
        }else{
            $scope.getTodos($scope.pageNumber);
        }    
    };
    
    $scope.salvar = function() {

        if (!($scope.Conta.senha === $scope.confirmaSenha)) {
            toastr.warning('As senhas devem ser iguais!');
        } else {
            console.log("Salvar Conta perfilDeAcessoContaId: " + $scope.Conta.perfilDeAcessoContaId);
            $http.post("./rest/ContaSource/Conta", $scope.Conta)
                .success(function(Conta) {
                console.log("Conta salvo = " + Conta);
                toastr.success("Usuário "+ $scope.Conta.nome +" cadastrado com sucesso!"); 
                window.location = "#/listaConta";
            }).error(function(data) {
                console.log("erro ao salvar Conta", data);
                toastr.warning("Erro ao salvar usuário!");
            });
        }
    };

    $scope.deletar = function(Conta) {
        $http({
            method: 'DELETE',
            data: Conta,
            url: './rest/ContaSource/Conta',
            headers: {'Content-Type': 'application/json; charset=UTF-8'}
        }).success(function(data) {
            console.log("Conta deletado" + data);
            toastr.success("Conta "+ Conta.nome +" deletado com sucesso!");
            $scope.getTodos(1);
        }).error(function(data) {
            console.log("erro ao deletar Conta " + data);
            toastr.warning("Erro ao deletar usuário!");
        });
    };
    $scope.carregarConta = function() {
        if ($routeParams.ContaId) {
            $http.get('./rest/ContaSource/Conta/' + $routeParams.ContaId)
                .success(function(Conta) {
                $scope.Conta = Conta;
                $scope.Conta.ativo = String(Conta.ativo);
            });
        }
    };

    $scope.getPerfisDeAcesso = function() {
        $http.get("./rest/perfilAcessoSource/perfilAcesso")
            .success(function(perfils, status) {
            $scope.perfisAcesso = perfils;
            //console.log(angular.toJson($scope.perfisAcesso, true));
        }).error(function(data, status) {
            console.log('Erro ao carregar perfis de acesso! ' + data);
        });
    };

    $scope.pontuarSenha = function() {
        var senha = $scope.Conta.senha;
        if (senha.length > 0) {
            if (senha.length >= 8 && senha.length <= 10) {
                tamanho = 6 * senha.length;
            } else {
                if (senha.length > 10) {
                    tamanho = 6 * 10;
                } else {
                    tamanho = 0;
                }
            }

            if (senha.charCodeAt(senha.length - 1) > 64 && senha.charCodeAt(senha.length - 1) < 91) {
                temMaiuscula = 20;
            }

            if (senha.charCodeAt(senha.length - 1) > 96 && senha.charCodeAt(senha.length - 1) < 123) {
                temMinuscula = 10;
            }

            if (senha.charCodeAt(senha.length - 1) > 47 && senha.charCodeAt(senha.length - 1) < 58) {
                temNumero = 15;
            }

            if (senha.charCodeAt(senha.length - 1) > 31 && senha.charCodeAt(senha.length - 1) < 48) {
                temSimbolo = 30;
            }

            if (senha.charCodeAt(senha.length - 1) > 57 && senha.charCodeAt(senha.length - 1) < 65) {
                temSimbolo = 30;
            }

            if ((senha.charCodeAt(senha.length - 1) > 91 && senha.charCodeAt(senha.length - 1) < 97) || senha.charCodeAt(senha.length - 1) > 122) {
                temSimbolo = 30;
            }

            resultado = tamanho + temMaiuscula + temMinuscula + temNumero + temSimbolo;
        } else {
            resultado = 0;
        }

        if (resultado < 40) {
            $scope.myStyle = {'background-color': '#ff0000', 'color': '#fff'};
            $scope.statusSenha = 'Senha Fraca';
        } else {
            if (resultado >= 40 && resultado < 80) {
                $scope.myStyle = {'background-color': '#ffff00', 'color': '#000'};
                $scope.statusSenha = 'Senha Média';
            } else {
                if (resultado >= 80) {
                    $scope.myStyle = {'background-color': '#66ff00', 'color': '#000'};
                    $scope.statusSenha = 'Senha Forte';
                }
            }
        }
    };
    function getNovoConta() {
        console.log('novo Conta');
        return {ativo: 'true'};
    }
    ;
}

function Ctrl($scope) {
    $scope.value = new Date(2010, 11, 28, 14, 57);
}
