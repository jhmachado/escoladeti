services = angular.module("services");

function movimentoService($http) {
    return {
        deletar: function(movimento) {
            return $http({
                method: 'DELETE',
                data: movimento,
                url: './rest/movimentacaoSource/movimento',
                headers: {'Content-Type': 'application/json; charset=UTF-8'}
            });
        },
        buscarPorNome: function(filtro) {
            return $http.get('./rest/movimentacaoSource/movimento?q=' + filtro.toUpperCase());
        },
        buscar: function(movimentoId) {
            return $http.get('./rest/movimentacaoSource/movimento/' + movimentoId);
        },
        salvar: function(movimento) {
            return $http.post('./rest/movimentacaoSource/movimento', {
            });
        },
        atualizar: function(movimento, $routeParams) {
            return $http.put("./rest/movimentacaoSource" + $routeParams.movimentoId, {
            });
        },
        listar: function(nrPagina) {
            return $http.get("./rest/movimentacaoSource/paginar/" + nrPagina);
        },
        buscarTodos: function() {
            return $http.get('./rest/movimentacaoSource/listar');
        }
    };
}

services.factory('movimentoService', ['$http', movimentoService]);

