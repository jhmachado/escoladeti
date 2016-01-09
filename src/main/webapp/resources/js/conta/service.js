services = angular.module("services");

function ContaService($http) {
    return {
        deletar: function(Conta) {
            return $http({
                method: 'DELETE',
                data: Conta,
                url: './rest/ContaSource/Conta',
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            });
        },
        buscarPorNome: function(filtro) {
            return $http.get('./rest/ContaSource/Conta?q=' + filtro.toUpperCase());
        },
        buscar: function(ContaId) {
            return $http.get('./rest/ContaSource/Conta/' + ContaId);
        },
        salvar: function(Conta) {
            return $http.post('./rest/ContaSource/Conta', {
                nome: Conta.nome,
                link: Conta.link,
                subMenu: Conta.subMenu
            });
        },
        atualizar: function(Conta, $routeParams) {
            return $http.put("./rest/ContaSource/" + $routeParams.ContaId, {
                nome: Conta.nome,
                link: Conta.link,
                subMenu: Conta.subMenu
            });
        },
        listar: function(nrPagina) {
            return $http.get("./rest/ContaSource/paginar/" + nrPagina);
        },
        listarSubMenus: function() {
            return $http.get('./rest/subMenuSource/subMenu');
        },
        buscarTodos: function() {
            return $http.get('./rest/ContaSource/listar');
        }
    };
}

services.factory('ContaService', ['$http', ContaService]);

