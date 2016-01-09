services = angular.module("services");

function CategoriaContaService($http) {
    return {
        deletar: function(CategoriaConta) {
            return $http({
                method: 'DELETE',
                data: CategoriaConta,
                url: './rest/CategoriaContaSource/CategoriaConta',
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            });
        },
        buscarPorNome: function(filtro) {
            return $http.get('./rest/CategoriaContaSource/CategoriaConta?q=' + filtro.toUpperCase());
        },
        buscar: function(CategoriaContaId) {
            return $http.get('./rest/CategoriaContaSource/CategoriaConta/' + CategoriaContaId);
        },
        salvar: function(CategoriaConta) {
            return $http.post('./rest/CategoriaContaSource/CategoriaConta', {
                nome: CategoriaConta.nome,
                link: CategoriaConta.link,
                subMenu: CategoriaConta.subMenu
            });
        },
        atualizar: function(CategoriaConta, $routeParams) {
            return $http.put("./rest/CategoriaContaSource/" + $routeParams.CategoriaContaId, {
                nome: CategoriaConta.nome,
                link: CategoriaConta.link,
                subMenu: CategoriaConta.subMenu
            });
        },
        listar: function(nrPagina) {
            return $http.get("./rest/CategoriaContaSource/paginar/" + nrPagina);
        },
        listarSubMenus: function() {
            return $http.get('./rest/subMenuSource/subMenu');
        },
        buscarTodos: function() {
            return $http.get('./rest/CategoriaContaSource/listar');
        }
    };
}

services.factory('CategoriaContaService', ['$http', CategoriaContaService]);

