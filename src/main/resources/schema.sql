DROP SCHEMA IF EXISTS public CASCADE;

CREATE SCHEMA public;

CREATE TABLE public.ROLES (
                                        ROLEID SERIAL PRIMARY KEY,
                                        role VARCHAR(15) NOT NULL UNIQUE
);

CREATE TABLE public.ACCOUNTS (
                                           accountId SERIAL PRIMARY KEY,
                                           username VARCHAR(15) NOT NULL UNIQUE,
                                           password VARCHAR(100) NOT NULL,
                                           email VARCHAR(30) NOT NULL UNIQUE,
                                           phone VARCHAR(11) NOT NULL UNIQUE,
                                           status INT NOT NULL DEFAULT 1,
                                           createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                           updateDate TIMESTAMP,
                                           ROLEID INT NOT NULL REFERENCES public.ROLES(ROLEID) ON DELETE CASCADE
);

CREATE TABLE public.POSTS (
                                        postId SERIAL PRIMARY KEY,
                                        accountId INT NOT NULL REFERENCES public.ACCOUNTS(accountId) ON DELETE CASCADE,
                                        context VARCHAR(255) NOT NULL,
                                        status INT NOT NULL DEFAULT 1,
                                        createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        updateDate TIMESTAMP
);

CREATE TABLE public.INTERACTIONS (
                                               interactionId SERIAL PRIMARY KEY,
                                               accountId INT NOT NULL REFERENCES public.ACCOUNTS(accountId) ON DELETE CASCADE,
                                               postId INT NOT NULL REFERENCES public.POSTS(postId) ON DELETE CASCADE,
                                               context VARCHAR(255) NOT NULL,
                                               type INT,
                                               status INT NOT NULL DEFAULT 1,
                                               createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                               updateDate TIMESTAMP
);

CREATE INDEX idx_accounts_roleid ON public.ACCOUNTS(ROLEID);
CREATE INDEX idx_posts_accountid ON public.POSTS(accountId);
CREATE INDEX idx_interactions_postid ON public.INTERACTIONS(postId);