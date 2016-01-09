services = angular.module("services");

function PerfilItemAcessoService($http) {
    return {
        deletar: function(perfilAcesso) {
            return $http({
                method: 'DELETE',
                data: perfilAcesso,
                url: './rest/perfilItemAcessoSource/perfilItemAcesso',
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            });
        },
        buscarPorNome: function(filtro) {
            return $http.get('./rest/perfilItemAcessoSource/perfilItemAcesso?q=' + filtro.toUpperCase());
        },
        buscar: function(perfilAcessoId) {
            return $http.get('./rest/perfilAcessoSource/perfilAcesso/' + perfilAcessoId);
        },
        buscarItensDoPerfil: function(perfilAcessoId){
            return $http.get('./rest/perfilItemAcessoSource/perfilAcesso/' + perfilAcessoId);
        },
        buscarItensDeAcesso: function(){
            return $http.get('./rest/itemAcessoSource/itemAcesso');
        },
        atualizar: function(perfilItemAcesso) {
            return $http.post('./rest/perfilItemAcessoSource/perfilItemAcesso/atualizar', {
                perfilAcesso: perfilItemAcesso.perfilAcesso,
                itemAcesso: perfilItemAcesso.itemAcesso
            });
        },
        salvar: function(perfilItemAcesso) {
            return $http.post('./rest/perfilItemAcessoSource/perfilItemAcesso/novo', {
                perfilAcesso: perfilItemAcesso.perfilAcesso,
                itemAcesso: perfilItemAcesso.itemAcesso
            });
        },
        listar: function(nrPagina) {
            return $http.get("./rest/perfilAcessoSource/paginar/" + nrPagina);
        },
        buscarTodos: function() {
            return $http.get('./rest/perfilItemAcessoSource/listar');
        }
    };
}

services.factory('perfilItemAcessoService', ['$http', PerfilItemAcessoService]);
