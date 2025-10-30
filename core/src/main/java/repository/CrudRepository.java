package repository;

import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Table;
import org.jooq.UpdatableRecord;
import org.jooq.impl.DSL;
import pagination.PageRequest;

import java.util.List;
import java.util.Optional;

public abstract class CrudRepository<R extends UpdatableRecord<R>, ID, DTO> {

    protected final DSLContext dsl;

    protected CrudRepository(DSLContext dsl) {
        this.dsl = dsl;
    }

    protected abstract Table<R> table();

    protected abstract Field<ID> idField();

    protected abstract R createRecordFromDto(DTO dto);

    protected abstract void updateRecordFromDto(R reg, DTO dto);


    public Optional<R> findByID(ID id) {
        return dsl.selectFrom(table())
                .where(idField().eq(id))
                .fetchOptional();
    }


    public List<R> findAll() {
        return dsl.selectFrom(table())
                .fetch();
    }
    public List<R> fidndPage(PageRequest pageRequest){
        return dsl.selectFrom(table())
                .limit(pageRequest.size())
                .offset(pageRequest.offSet())
                .fetch();
    }

    public int count(){
        return Optional.ofNullable(
                dsl.selectCount()
                        .from(table())
                        .fetchOne(0, int.class)
        ).orElse(0);
    }

    public R insert(DTO dto) {
        return dsl.transactionResult(config -> {
            var reg = createRecordFromDto(dto);
            reg.attach(config);
            reg.store();

            return reg;
        });
    }

    public Optional<R> updateById(ID id, DTO dto) {
        return dsl.transactionResult(config -> {
            DSLContext conn = DSL.using(config);

            var reg = conn.selectFrom(table())
                    .where(idField().eq(id))
                    .fetchOne();

            if (reg == null) return Optional.empty();

            updateRecordFromDto(reg, dto);
            reg.attach(config);
            reg.store();

            return Optional.of(reg);
        });
    }

    public boolean deleteById(ID id) {
        return dsl.transactionResult(config -> {
            DSLContext conn = DSL.using(config);

            return conn.deleteFrom(table())
                    .where(idField().eq(id))
                    .execute() > 0;
        });
    }
}
