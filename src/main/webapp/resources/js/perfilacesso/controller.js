var controllers = angular.module('controllers');

function PerfilAcessoController($scope, $routeParams, perfilAcessoService) {
    console.log('carregando controller de perfil');

    $scope.itensSelecionados = [];
    $scope.perfilAcesso = {};

    $scope.deletar = function(perfilAcesso) {
        console.log('deletando perfil de acesso ' + JSON.stringify(perfilAcesso));

        BootstrapDialog.confirm('Deseja realmente deletar o perfil de acesso: <b>' + perfilAcesso.nome + '</b>?', function(result) {
            if (result) {
                perfilAcessoService.deletar(perfilAcesso)
                        .success(function(data, status) {
                            $scope.getTodos(1);
                            console.log('perfil de acesso deletado');
                            toastr.success(perfilAcesso.nome + " deletado com sucesso.");
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
        if (!$routeParams.perfilAcessoId) {
            $scope.perfilAcesso = getNovoPerfilAcesso();
            return;//se não tiver id não buscar
        }
        perfilAcessoService.buscar($routeParams.perfilAcessoId)
                .success(function(perfilAcesso, status) {
                    $scope.perfilAcesso = perfilAcesso;
                });
    };

    $scope.editar = function(perfilAcesso) {
        window.location = '#/cadastroperfilacesso/' + perfilAcesso.id;
    };

    $scope.buscarPerfilContendoNome = function() {
        console.log('chegou aqui' + $scope.busca);
        perfilAcessoService.buscarPorNome($scope.busca)
                .success(function(perfilAcesso, status) {
                    console.log(perfilAcesso);
                    $scope.perfisDeAcesso = perfilAcesso;
                })
                .error(function(data, status) {
                    toastr.error(data.message);
                });
    };

    $scope.salvar = function() {
        $scope.perfilItemAcesso = {
            perfilAcesso: $scope.perfilAcesso,
            itemAcesso: $scope.itensSelecionados
        };

        if ($routeParams.perfilAcessoId) {
            perfilAcessoService.atualizar($scope.perfilAcesso, $routeParams)
                    .success(function(perfilAcesso, status) {
                        $scope.perfilAcesso = getNovoPerfilAcesso();
                        toastr.success('Perfil de acesso ' + perfilAcesso.nome + ' salvo com sucesso.');
                        window.location = '#/listaperfilacesso';
                    })
                    .error(function(data, status) {
                        console.log('Perfil de acesso não salvo ', data);
                        toastr.warning(data.message);
                        console.log(data.messageDeveloper);
                    });
        } else {
            perfilAcessoService.salvar($scope.perfilItemAcesso)
                    .success(function(perfilAcesso, status) {
                        toastr.success("Perfil de acesso " + perfilAcesso.nome + ' salvo com sucesso ');
                        window.location = '#/listaperfilacesso';
                    })
                    .error(function(data, status) {
                        console.log('Perfil de acesso não salvo ', data);
                        toastr.warning(data.message);
                        console.log(data.messageDeveloper);
                        console.log(status);
                    });
        }

    };

    $scope.getTodos = function(numeroPagina) {
        perfilAcessoService.listar(numeroPagina)
                .success(function(listaPerfisDeAcesso, status) {
                    $scope.perfisDeAcesso = listaPerfisDeAcesso.list;
                })
                .error(function(data, status) {
                    console.log('erro ao buscar perfis de acesso ' + data);
                });
    };

    function getNovoPerfilAcesso() {
        return {
            nome: ''
        };
    }

    $scope.voltar = function() {
        $scope.perfilAcesso = {};
        window.location = '#/listaperfilacesso';
    };

}

controllers.controller('PerfilAcessoController', ['$scope', '$routeParams', 'perfilAcessoService', PerfilAcessoController]);