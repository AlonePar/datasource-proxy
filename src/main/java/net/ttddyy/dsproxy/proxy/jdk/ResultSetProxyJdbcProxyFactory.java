package net.ttddyy.dsproxy.proxy.jdk;

import net.ttddyy.dsproxy.ConnectionInfo;
import net.ttddyy.dsproxy.proxy.InterceptorHolder;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

import static net.ttddyy.dsproxy.proxy.jdk.StatementResultSetResultInvocationHandler.statementResultSetResultProxy;

/**
 * Extension of {@link net.ttddyy.dsproxy.proxy.jdk.JdkJdbcProxyFactory} that also proxies any
 * {@link java.sql.ResultSet} results so that they can be consumed more than once.
 *
 * @author Liam Williams
 * @since 1.4
 */
public class ResultSetProxyJdbcProxyFactory extends JdkJdbcProxyFactory {

    @Override
    public Statement createStatement(Statement statement, InterceptorHolder interceptorHolder,
                                     ConnectionInfo connectionInfo, Connection proxyConnection) {
        return super.createStatement(statementResultSetResultProxy(statement, Statement.class),
                interceptorHolder, connectionInfo, proxyConnection);
    }

    @Override
    public PreparedStatement createPreparedStatement(PreparedStatement preparedStatement, String query,
                                                     InterceptorHolder interceptorHolder, ConnectionInfo connectionInfo,
                                                     Connection proxyConnection) {
        return super.createPreparedStatement(statementResultSetResultProxy(preparedStatement, PreparedStatement.class),
                query, interceptorHolder, connectionInfo, proxyConnection);
    }

    @Override
    public CallableStatement createCallableStatement(CallableStatement callableStatement, String query,
                                                     InterceptorHolder interceptorHolder, ConnectionInfo connectionInfo,
                                                     Connection proxyConnection) {
        return super.createCallableStatement(statementResultSetResultProxy(callableStatement, CallableStatement.class),
                query, interceptorHolder, connectionInfo, proxyConnection);
    }
}
