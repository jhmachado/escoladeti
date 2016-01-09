function PerfilAcessoFactory($resource) {
    return $resource("./rest/perfilacesso/:id/:acao/:pagina/:tipo", {id: '@id'},{
		
        update: {method: 'PUT'},

    });
}

angular.module('services')
	.factory('PerfilAcessoFactory', ['$resource', PerfilAcessoFactory]);