-- used in tests that use HSQL
create table IF NOT EXISTS oauth_client_details
(
    client_id               VARCHAR(256) PRIMARY KEY,
    resource_ids            VARCHAR(256),
    client_secret           VARCHAR(256),
    scope                   VARCHAR(256),
    authorized_grant_types  VARCHAR(256),
    web_server_redirect_uri VARCHAR(256),
    authorities             VARCHAR(256),
    access_token_validity   INTEGER,
    refresh_token_validity  INTEGER,
    additional_information  VARCHAR(4096),
    autoapprove             VARCHAR(256)
);

create table IF NOT EXISTS oauth_client_token
(
    token_id          VARCHAR(256),
    token LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256)
);

create table IF NOT EXISTS oauth_access_token
(
    token_id          VARCHAR(256),
    token LONGVARBINARY,
    authentication_id VARCHAR(256) PRIMARY KEY,
    user_name         VARCHAR(256),
    client_id         VARCHAR(256),
    authentication LONGVARBINARY,
    refresh_token     VARCHAR(256)
);

create table IF NOT EXISTS oauth_refresh_token
(
    token_id VARCHAR(256),
    token LONGVARBINARY,
    authentication LONGVARBINARY
);

create table IF NOT EXISTS oauth_code
(
    code VARCHAR(256),
    authentication LONGVARBINARY
);

create table IF NOT EXISTS oauth_approvals
(
    userId         VARCHAR(256),
    clientId       VARCHAR(256),
    scope          VARCHAR(256),
    status         VARCHAR(10),
    expiresAt      TIMESTAMP,
    lastModifiedAt TIMESTAMP
);

insert into oauth_client_details(client_id, resource_ids, client_secret, scope, authorized_grant_types,
                                 web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity,
                                 additional_information, autoapprove)
values ('client1',
        'resource1',
        '$2a$10$8dX0cd5hb3is1tsPrPUWT.TDjB4LjdbmmfD/r0Bq10GC6kPeOQT7W',
        'account',
        'authorization_code,refresh_token',
        'http://localhost:9091/oauth2/callback',
        null,
        null,
        null,
        null,
        'true'),
       ('client2',
        'resource2',
        '$2a$10$8dX0cd5hb3is1tsPrPUWT.TDjB4LjdbmmfD/r0Bq10GC6kPeOQT7W',
        'account',
        'authorization_code,refresh_token',
        'http://localhost:9092/oauth2/callback',
        null,
        null,
        null,
        null,
        'true');

create table IF NOT EXISTS users
(
    `id`       bigint(20)  NOT NULL AUTO_INCREMENT,
    `username` varchar(20) NOT NULL,
    `password` char(60)    NOT NULL
);

create table IF NOT EXISTS user_roles
(
    user_id bigint not null,
    roles   varchar(255)
);

insert into users(username, password)
values ('user', '$2a$10$8dX0cd5hb3is1tsPrPUWT.TDjB4LjdbmmfD/r0Bq10GC6kPeOQT7W')

