function CategoriaContaController($scope, $http, $routeParams) {
    $scope.novo = function() {
        $scope.getPerfisDeAcesso();
        if ($routeParams.CategoriaContaId) {
            $scope.carregarCategoriaConta();
        } else {
            $scope.CategoriaConta = getNovoCategoriaConta();
           // console.log(angular.toJson($scope.CategoriaConta, true));
            window.location = '#/cadastroCategoriaConta';
        }
    };
    $scope.editar = function(CategoriaConta) {
        console.log('Editar CategoriaConta');
        window.location = '#/cadastroCategoriaConta/' + CategoriaConta.id;
    };
    $scope.voltar = function () {
        window.location = '#/listaCategoriaConta';
    };
    
    $scope.getTodos = function(numeroPagina) {
        console.log(numeroPagina);
        $http.get('./rest/CategoriaContaSource/listar/pag/' + numeroPagina)
            .success(function(CategoriaContas) {
                $scope.pagina = CategoriaContas;
                console.log('CategoriaContas ' + CategoriaContas);
            }).error(function(data) {
                console.log('erro ao buscar CategoriaContas ' + data);
            });
    };
    
    $scope.buscaCategoriaContasContendoNome = function() {
        console.log($scope.busca);
        if(!$scope.busca.empty){
        $http.get('./rest/CategoriaContaSource/CategoriaContas?q=' + $scope.busca.toUpperCase())
            .then(function(CategoriaContas) {
                console.log(CategoriaContas.data.list);
                $scope.pagina = CategoriaContas.data;
            });
        }else{
            $scope.getTodos($scope.pageNumber);
        }    
    };
    
    $scope.salvar = function() {

        if (!($scope.CategoriaConta.senha === $scope.confirmaSenha)) {
            toastr.warning('As senhas devem ser iguais!');
        } else {
            console.log("Salvar CategoriaConta perfilDeAcessoCategoriaContaId: " + $scope.CategoriaConta.perfilDeAcessoCategoriaContaId);
            $http.post("./rest/CategoriaContaSource/CategoriaConta", $scope.CategoriaConta)
                .success(function(CategoriaConta) {
                console.log("CategoriaConta salvo = " + CategoriaConta);
                toastr.success("Usuário "+ $scope.CategoriaConta.nome +" cadastrado com sucesso!"); 
                window.location = "#/listaCategoriaConta";
            }).error(function(data) {
                console.log("erro ao salvar CategoriaConta", data);
                toastr.warning("Erro ao salvar usuário!");
            });
        }
    };

    $scope.deletar = function(CategoriaConta) {
        $http({
            method: 'DELETE',
            data: CategoriaConta,
            url: './rest/CategoriaContaSource/CategoriaConta',
            headers: {'Content-Type': 'application/json; charset=UTF-8'}
        }).success(function(data) {
            console.log("CategoriaConta deletado" + data);
            toastr.success("CategoriaConta "+ CategoriaConta.nome +" deletado com sucesso!");
            $scope.getTodos(1);
        }).error(function(data) {
            console.log("erro ao deletar CategoriaConta " + data);
            toastr.warning("Erro ao deletar usuário!");
        });
    };
    $scope.carregarCategoriaConta = function() {
        if ($routeParams.CategoriaContaId) {
            $http.get('./rest/CategoriaContaSource/CategoriaConta/' + $routeParams.CategoriaContaId)
                .success(function(CategoriaConta) {
                $scope.CategoriaConta = CategoriaConta;
                $scope.CategoriaConta.ativo = String(CategoriaConta.ativo);
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
        var senha = $scope.CategoriaConta.senha;
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
    function getNovoCategoriaConta() {
        console.log('novo CategoriaConta');
        return {ativo: 'true'};
    }
    ;
}

function Ctrl($scope) {
    $scope.value = new Date(2010, 11, 28, 14, 57);
}
