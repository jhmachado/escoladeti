var controllers = angular.module('controllers');

function PerfilItemAcessoController($scope, $routeParams, perfilItemAcessoService) {
    console.log('carregando controller perfil item acesso');

    $scope.itensSelecionados = [];
    $scope.perfilAcesso = {};


    $scope.deletar = function(perfilAcesso) {
//        console.log('deletando perfil de acesso ' + JSON.stringify(perfilAcesso));

        BootstrapDialog.confirm('Deseja realmente deletar o perfil de acesso: <b>' + perfilAcesso.nome + '</b>?', function(result) {
            if (result) {
                perfilItemAcessoService.deletar(perfilAcesso)
                        .success(function(perfilAcesso, status) {
                            $scope.getTodos(1);
                            console.log('perfil de acesso deletado');
                            toastr.success("perfil deletado com sucesso.");
                        })
                        .error(function(data, status) {
                            console.log('erro ao deletar perfil de acesso ' + data);
                            console.log(data.messageDeveloper);
                            toastr.error(data.message);
                        });
            }
        });

    };

    $scope.novo = function() {
        $scope.perfilAcesso = getNovoPerfilAcesso();
        window.location = '#/cadastroperfilacesso';
    };

    $scope.carregarPerfilAcesso = function() {
        perfilItemAcessoService.buscarItensDeAcesso()
                .success(function(itensDeAcesso, status) {
                    $scope.itensDeAcesso = itensDeAcesso;
                });
        if (!$routeParams.perfilAcessoId) {
            $scope.perfilItemAcesso = getNovoPerfilAcesso();
            return;//se não tiver id não buscar
        }
        perfilItemAcessoService.buscar($routeParams.perfilAcessoId)
                .success(function(perfilAcesso, status) {
                    $scope.perfilAcesso = perfilAcesso;
                });
        perfilItemAcessoService.buscarItensDoPerfil($routeParams.perfilAcessoId)
                .success(function(itensDoPerfil, status) {
                    $scope.itensSelecionados = itensDoPerfil;
                    console.log($scope.itensSelecionados);
                });
    };

    $scope.editar = function(perfilAcesso) {
        window.location = '#/cadastroperfilacesso/' + perfilAcesso.id;
    };

    $scope.buscaPerfilAcessoContendoNome = function() {
        console.log($scope.busca);
        perfilItemAcessoService.buscarPorNome($scope.busca)
                .then(function(retorno) {
                    console.log(retorno.data.list);
                    $scope.perfilItemAcesso = retorno.data;
                });
    };

    $scope.salvar = function() {
        $scope.perfilItemAcesso = {
            perfilAcesso: $scope.perfilAcesso,
            itemAcesso: $scope.itensSelecionados
        };
        if ($routeParams.perfilAcessoId) {
            perfilItemAcessoService.atualizar($scope.perfilItemAcesso)
                    .success(function(perfilAcesso, status) {
                        $scope.perfilItemAcesso = getNovoPerfilAcesso();
                        toastr.success('Perfil de acesso ' + perfilAcesso.nome + ' salvo com sucesso.');
                        window.location = '#/listaperfilacesso';
                    })
                    .error(function(data, status) {
                        console.log('Perfil de acesso não salvo ', data);
                        toastr.warning(data.message);
                        console.log(data.messageDeveloper);
                    });
        } else {
            perfilItemAcessoService.salvar($scope.perfilItemAcesso)
                    .success(function(perfilAcesso, status) {
                        $scope.perfilItemAcesso = getNovoPerfilAcesso();
                        toastr.success('Perfil de acesso ' + perfilAcesso.nome + ' salvo com sucesso.');
                        window.location = '#/listaperfilacesso';
                    })
                    .error(function(data, status) {
                        console.log('Perfil de acesso não salvo ', data);
                        toastr.warning(data.message);
                        console.log(data.messageDeveloper);
                    });
        }

    };

    $scope.getTodos = function(numeroPagina) {
        perfilItemAcessoService.listar(numeroPagina)
                .success(function(listaPerfisDeAcesso, status) {
                    $scope.perfisDeAcesso = listaPerfisDeAcesso.list;
                })
                .error(function(data, status) {
                    console.log('erro ao buscar perfis de acesso ' + data);
                });
    };

    function getNovoPerfilAcesso() {
        console.log('novo perfil de acesso');
        return {
            perfilAcesso: '',
            itemAcesso: ''
        };
    }

    $scope.voltar = function() {
        $scope.perfilAcesso = {};
        window.location = '#/listaperfilacesso';
    };

}

controllers.controller('PerfilItemAcessoController', ['$scope', '$routeParams', 'perfilItemAcessoService', PerfilItemAcessoController]);