services = angular.module("services");

function TipoContaService($http) {
    return {
        deletar: function(tipoConta) {
            return $http({
                method: 'DELETE',
                data: tipoConta,
                url: './rest/TipoContaSource/TipoConta',
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            });
        },
        buscarPorNome: function(filtro) {
            return $http.get('./rest/TipoContaSource/TipoConta?q=' + filtro.toUpperCase());
        },
        buscar: function(tipoContaId) {
            return $http.get('./rest/TipoContaSource/TipoConta/' + tipoContaId);
        },
        salvar: function(tipoConta) {
            return $http.post('./rest/TipoContaSource/TipoConta', {
                nome: tipoConta.nome,
                link: tipoConta.link,
                subMenu: tipoConta.subMenu
            });
        },
        atualizar: function(tipoConta, $routeParams) {
            return $http.put("./rest/TipoContaSource/" + $routeParams.tipoContaId, {
                nome: tipoConta.nome,
                link: tipoConta.link,
                subMenu: tipoConta.subMenu
            });
        },
        listar: function(nrPagina) {
            return $http.get("./rest/TipoContaSource/paginar/" + nrPagina);
        },
        listarSubMenus: function() {
            return $http.get('./rest/subMenuSource/subMenu');
        },
        buscarTodos: function() {
            return $http.get('./rest/TipoContaSource/listar');
        }
    };
}

services.factory('TipoContaService', ['$http', TipoContaService]);

