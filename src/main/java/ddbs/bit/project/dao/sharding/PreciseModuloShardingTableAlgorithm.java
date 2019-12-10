package ddbs.bit.project.dao.sharding;

import org.apache.shardingsphere.api.sharding.standard.PreciseShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.standard.PreciseShardingValue;

import java.util.Collection;

public class PreciseModuloShardingTableAlgorithm implements PreciseShardingAlgorithm<Long> {
    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Long> preciseShardingValue) {
        for (String each: collection) {
            long val = preciseShardingValue.getValue() % 2;
            val = (val==0)?1:0;
            if(each.endsWith(val + ""))
                return each;
        }
        throw new UnsupportedOperationException();
    }
}
