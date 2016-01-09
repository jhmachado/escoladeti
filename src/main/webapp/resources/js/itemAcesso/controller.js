var controllers = angular.module('controllers');

function ItemAcessoController($scope, $routeParams, itemAcessoService) {
    $scope.subMenus = [];

    $scope.deletar = function(itemAcesso) {
        BootstrapDialog.confirm('Deseja realmente deletar o item de acesso: <b>' + itemAcesso.nome + '</b>?', function(result) {
            if (result) {
                itemAcessoService.deletar(itemAcesso)
                        .success(function(data, status) {
                            $scope.getTodos(1);
                            toastr.success(itemAcesso.nome + " deletado com sucesso.");
                        })
                        .error(function(data, status) {
                            toastr.error(data.message);
                        });
            }
        });

    };

    $scope.buscarItemContendoNome = function() {
        itemAcessoService.buscarPorNome($scope.busca)
                .success(function(itemAcesso, status) {
                    console.log(itemAcesso);
                    $scope.itensDeAcesso = itemAcesso.list;
                })
                .error(function(data, status) {
                    toastr.error(data.message);
                });
    };

    $scope.novo = function() {
        $scope.itemAcesso = getNovoItemAcesso();
        window.location = '#/cadastroitemacesso';
    };

    $scope.carregarItemAcesso = function() {
        $scope.getSubMenus();
        if (!$routeParams.itemAcessoId) {
            $scope.itemAcesso = getNovoItemAcesso();
            return;//se não tiver id não buscar
        }

        itemAcessoService.buscar($routeParams.itemAcessoId)
                .success(function(itemAcesso, status) {
                    $scope.itemAcesso = itemAcesso;
                })
                .error(function(data, status) {
                    toastr.error(data.message);
                });
    };

    $scope.editar = function(itemAcesso) {
        window.location = '#/cadastroitemacesso/' + itemAcesso.id;
    };

    $scope.salvar = function() {
        if ($routeParams.itemAcessoId) {
            console.log($scope.itemAcesso);
            console.log($routeParams);
            itemAcessoService.atualizar($scope.itemAcesso, $routeParams)
                    .success(function(itemAcesso, status) {
                        $scope.itemAcesso = getNovoItemAcesso();
                        toastr.success('Item de acesso ' + itemAcesso.nome + ' salvo com sucesso.');
                        window.location = '#/listaitemacesso';
                        return true;
                    })
                    .error(function(data, status) {
                        console.log('item de acesso não salvo ', data);
                        toastr.warning(data.message);
                        console.log(data.messageDeveloper);
                        return false;
                    });
        } else {
            itemAcessoService.salvar($scope.itemAcesso)
                    .success(function(itemAcesso, status) {
                        $scope.itemAcesso = getNovoItemAcesso();
                        toastr.success('Item de acesso ' + itemAcesso.nome + ' salvo com sucesso.');
                        window.location = '#/listaitemacesso';
                    })
                    .error(function(data, status) {
                        console.log('item de acesso não salvo ', data);
                        toastr.warning(data.message);
                        console.log(data.messageDeveloper);
                    });
        }
    };

    $scope.getTodos = function(numeroPagina) {
        console.log(numeroPagina);
        itemAcessoService.listar(numeroPagina)
                .success(function(listaItensDeAcesso, status) {
                    console.log(listaItensDeAcesso);
                    $scope.itensDeAcesso = listaItensDeAcesso;
                })
                .error(function(data, status) {
                    toastr.error(data.message);
                });
    };

    $scope.getSubMenus = function() {
        itemAcessoService.listarSubMenus()
                .success(function(listaSubMenus, status) {
                    $scope.subMenus = listaSubMenus;
                })
                .error(function(data, status) {
                    toastr.error(data.message);
                });
    };

    function getNovoItemAcesso() {
        console.log('novo item de acesso');
        return {};
    }

    $scope.voltar = function() {
        $scope.itemAcesso = {};
        window.location = '#/listaitemacesso';
    };

}

controllers.controller('ItemAcessoController', ['$scope', '$routeParams', 'itemAcessoService', ItemAcessoController]);