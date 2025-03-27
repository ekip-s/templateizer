CREATE EXTENSION IF NOT EXISTS "pgcrypto";


CREATE TABLE if not exists categories (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name varchar(100) NOT NULL UNIQUE,
    creator varchar(100),
    immutable boolean,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);


CREATE TABLE if not exists templates (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT NOT NULL,
    content TEXT NOT NULL,
    category_id UUID REFERENCES categories(id) ON DELETE SET NULL,
    is_public BOOLEAN NOT NULL DEFAULT false,
    user_id UUID NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW()
);

CREATE TABLE if not exists user_template_mapping (
    user_id TEXT NOT NULL,
    template_id UUID NOT NULL REFERENCES templates(id) ON DELETE CASCADE,
    is_favorite BOOLEAN NOT NULL DEFAULT false,
    rating SMALLINT CHECK (rating BETWEEN 1 AND 5),
    created_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT NOW(),
    PRIMARY KEY (user_id, template_id)
);

CREATE INDEX if not exists idx_templates_category ON templates(category_id) WHERE category_id IS NOT NULL;
CREATE INDEX if not exists idx_templates_public_user ON templates(is_public, user_id);
CREATE INDEX if not exists idx_utm_user_fav ON user_template_mapping(user_id, is_favorite) WHERE is_favorite = true;
CREATE INDEX if not exists idx_utm_template_rating ON user_template_mapping(template_id, rating);

INSERT INTO categories (name, creator, immutable)
VALUES ('Другое', 'admin', true)
ON CONFLICT (name)
DO NOTHING;