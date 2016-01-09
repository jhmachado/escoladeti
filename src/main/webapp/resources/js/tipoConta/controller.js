function TipoContaController($scope, $http, $routeParams) {
    $scope.novo = function() {
        $scope.getPerfisDeAcesso();
        if ($routeParams.TipoContaId) {
            $scope.carregarTipoConta();
        } else {
            $scope.TipoConta = getNovoTipoConta();
           // console.log(angular.toJson($scope.TipoConta, true));
            window.location = '#/cadastroTipoConta';
        }
    };
    $scope.editar = function(TipoConta) {
        console.log('Editar TipoConta');
        window.location = '#/cadastroTipoConta/' + TipoConta.id;
    };
    $scope.voltar = function () {
        window.location = '#/listaTipoConta';
    };
    
    $scope.getTodos = function(numeroPagina) {
        console.log(numeroPagina);
        $http.get('./rest/TipoContaSource/listar/pag/' + numeroPagina)
            .success(function(TipoContas) {
                $scope.pagina = TipoContas;
                console.log('TipoContas ' + TipoContas);
            }).error(function(data) {
                console.log('erro ao buscar TipoContas ' + data);
            });
    };
    
    $scope.buscaTipoContasContendoNome = function() {
        console.log($scope.busca);
        if(!$scope.busca.empty){
        $http.get('./rest/TipoContaSource/TipoContas?q=' + $scope.busca.toUpperCase())
            .then(function(TipoContas) {
                console.log(TipoContas.data.list);
                $scope.pagina = TipoContas.data;
            });
        }else{
            $scope.getTodos($scope.pageNumber);
        }    
    };
    
    $scope.salvar = function() {

        if (!($scope.TipoConta.senha === $scope.confirmaSenha)) {
            toastr.warning('As senhas devem ser iguais!');
        } else {
            console.log("Salvar TipoConta perfilDeAcessoTipoContaId: " + $scope.TipoConta.perfilDeAcessoTipoContaId);
            $http.post("./rest/TipoContaSource/TipoConta", $scope.TipoConta)
                .success(function(TipoConta) {
                console.log("TipoConta salvo = " + TipoConta);
                toastr.success("Usuário "+ $scope.TipoConta.nome +" cadastrado com sucesso!"); 
                window.location = "#/listaTipoConta";
            }).error(function(data) {
                console.log("erro ao salvar TipoConta", data);
                toastr.warning("Erro ao salvar usuário!");
            });
        }
    };

    $scope.deletar = function(TipoConta) {
        $http({
            method: 'DELETE',
            data: TipoConta,
            url: './rest/TipoContaSource/TipoConta',
            headers: {'Content-Type': 'application/json; charset=UTF-8'}
        }).success(function(data) {
            console.log("TipoConta deletado" + data);
            toastr.success("TipoConta "+ TipoConta.nome +" deletado com sucesso!");
            $scope.getTodos(1);
        }).error(function(data) {
            console.log("erro ao deletar TipoConta " + data);
            toastr.warning("Erro ao deletar usuário!");
        });
    };
    $scope.carregarTipoConta = function() {
        if ($routeParams.TipoContaId) {
            $http.get('./rest/TipoContaSource/TipoConta/' + $routeParams.TipoContaId)
                .success(function(TipoConta) {
                $scope.TipoConta = TipoConta;
                $scope.TipoConta.ativo = String(TipoConta.ativo);
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
        var senha = $scope.TipoConta.senha;
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
    function getNovoTipoConta() {
        console.log('novo TipoConta');
        return {ativo: 'true'};
    }
    ;
}

function Ctrl($scope) {
    $scope.value = new Date(2010, 11, 28, 14, 57);
}
